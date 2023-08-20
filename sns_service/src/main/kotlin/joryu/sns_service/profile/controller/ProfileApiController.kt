package joryu.sns_service.profile.controller

import joryu.sns_service.profile.dto.request.ProfileCreateRequest
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest
import joryu.sns_service.profile.dto.response.ProfileInfoResponse
import joryu.sns_service.profile.service.ProfileService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/profile")
@RequiredArgsConstructor
class ProfileApiController(
        val profileService: ProfileService
) {

    @PostMapping()
    fun createProfile(@RequestBody profileCreateRequest: ProfileCreateRequest): ResponseEntity<String> {
        profileService.create(profileCreateRequest)
        return ResponseEntity("ok", HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findProfile(@PathVariable id: Long): ResponseEntity<ProfileInfoResponse> {
        return ResponseEntity(profileService.findOnyById(id), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateProfile(@PathVariable id: Long, @RequestBody profileUpdateRequest: ProfileUpdateRequest): ResponseEntity<ProfileInfoResponse> {
        return ResponseEntity(profileService.update(id, profileUpdateRequest), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteProfile(@PathVariable id: Long): ResponseEntity<String> {
        profileService.delete(id)
        return ResponseEntity("ok", HttpStatus.OK)
    }
}