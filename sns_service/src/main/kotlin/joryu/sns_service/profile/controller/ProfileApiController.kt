package joryu.sns_service.profile.controller

import joryu.sns_service.profile.dto.request.ProfileCreateRequest
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest
import joryu.sns_service.profile.dto.response.ProfileInfoResponse
import joryu.sns_service.profile.entity.Profile
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
    fun createProfile(@RequestBody profileCreateRequest: ProfileCreateRequest): ResponseEntity<Profile> {
        val profile = profileService.create(profileCreateRequest)
        return ResponseEntity(profile, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findProfile(@PathVariable id: Long): ResponseEntity<ProfileInfoResponse> {
        val profile = profileService.findOneById(id)
        return ResponseEntity(profile, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateProfile(@PathVariable id: Long, @RequestBody profileUpdateRequest: ProfileUpdateRequest): ResponseEntity<ProfileInfoResponse> {
        val profile = profileService.update(id, profileUpdateRequest)
        return ResponseEntity(profile, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteProfile(@PathVariable id: Long): ResponseEntity<Any> {
        profileService.delete(id)
        return ResponseEntity.ok().build()
    }
}