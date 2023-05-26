package com.example.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.common.MainViewModel
import com.example.core.ui.utility.Constants
import com.example.core.ui.utility.fragmentViewBinding
import com.example.feature.login.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.GoogleAuthType
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by fragmentViewBinding(FragmentLoginBinding::bind)
    private val activityViewModel: MainViewModel by activityViewModels()
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCurrentUser()

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.setBottomBarVisibility(false)

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (it) {
                toHomeFragment()
            }
        }
    }


    override fun onStart() {
        super.onStart()
        binding.signInButton.setOnClickListener {
            loginWithGoogle()
        }
    }


    private fun loginWithGoogle() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.CLIENT_ID)
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val signInIntent: Intent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            if (completedTask.isSuccessful) {
                val account: GoogleSignInAccount? =
                    completedTask.getResult(ApiException::class.java)
                val token: String = account?.idToken!!
                val app: App = App.create(Constants.APP_ID)
                runBlocking {
                    val user = app.login(
                        io.realm.kotlin.mongodb.Credentials.google(
                            token,
                            GoogleAuthType.ID_TOKEN
                        )
                    )
                    viewModel.setCurrentUser(true)
                }
            } else {
                Log.e("AUTH", "Google Auth failed: ${completedTask.exception}")

            }
        } catch (e: ApiException) {
            Log.e("AUTH", "Failed to authenticate using Google OAuth: " + e.message)

        }
    }


    private fun toHomeFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}