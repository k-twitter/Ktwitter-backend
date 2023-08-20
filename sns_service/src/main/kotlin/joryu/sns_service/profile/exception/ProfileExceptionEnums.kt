package joryu.sns_service.profile.exception

import org.springframework.http.HttpStatus

enum class ProfileExceptionEnums(val status: HttpStatus, val message: String) {
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 Profile이 존재하지 않습니다.")
}