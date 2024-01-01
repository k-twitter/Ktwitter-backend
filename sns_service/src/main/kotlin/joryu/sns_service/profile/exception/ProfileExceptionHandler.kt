package joryu.sns_service.profile.exception

import joryu.sns_service.common.dto.response.CommonResponseBody
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ProfileExceptionHandler {
    @ExceptionHandler(ProfileBaseException::class)
    protected fun handleBaseException(e: ProfileBaseException): ResponseEntity<CommonResponseBody> {
        return ResponseEntity.status(e.profileExceptionEnums.status)
                .body(CommonResponseBody(e.profileExceptionEnums.status.value(), e.profileExceptionEnums.message))
    }
}