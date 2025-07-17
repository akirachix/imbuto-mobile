import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.Imbuto.imbutohub.*
sealed class Screen(val route: String) {
    object TeaserFlow : Screen("teaser_flow")
    object ForgotPassword : Screen("forgot_password")
    object PasswordRecovery : Screen("password_recovery")
    object PasswordRecoveryEmail : Screen("password_recovery_email")
    object PasswordReset : Screen("password_reset")
    object VerificationScreen: Screen("verification")
    object PasswordResetSuccess : Screen("password_reset_success")
}
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.TeaserFlow.route) {
        composable(Screen.TeaserFlow.route) {
            TeaserFlow(
                onFinish = {
                    navController.navigate(Screen.ForgotPassword.route) {
                        popUpTo(Screen.TeaserFlow.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.ForgotPassword.route) {
            ForgotPassword(
                onPhoneSelected = {
                    navController.navigate(Screen.PasswordRecovery.route)
                },
                onEmailSelected = {
                    navController.navigate(Screen.PasswordRecoveryEmail.route)
                },
                onGoBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.PasswordRecovery.route) {
            PasswordRecovery(
                onReset = {
                    navController.navigate(Screen.VerificationScreen.route)
                },
                onGoBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(Screen.VerificationScreen.route) {
            VerificationScreen(
                onVerify = {
                    navController.navigate(Screen.PasswordReset.route)
                },
                onGoBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.PasswordRecoveryEmail.route) {
            PasswordRecoveryEmail(
                onReset = {
                    navController.navigate(Screen.PasswordReset.route)
                },
                onGoBack = {
                    navController.popBackStack()
                },
                onEmailSubmitted = {}
            )
        }
        composable(Screen.PasswordReset.route) {
            ResetPassword(
                onNext = {
                    navController.navigate(Screen.PasswordResetSuccess.route) {
                        }

                },
                onGoBack = {
                    navController.navigate(Screen.ForgotPassword.route) {
                        popUpTo(Screen.PasswordReset.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.PasswordResetSuccess.route) {
            PasswordResetSuccess(
                onContinueClicked = {
                    // You can change this to navigate somewhere else if needed
                    navController.navigate(Screen.TeaserFlow.route) {
                        popUpTo(Screen.PasswordResetSuccess.route) { inclusive = true }
                    }
                },
                onGoBackClicked = {
                    navController.popBackStack(Screen.ForgotPassword.route,
                        inclusive = false)
                }
            )
        }
    }
}









