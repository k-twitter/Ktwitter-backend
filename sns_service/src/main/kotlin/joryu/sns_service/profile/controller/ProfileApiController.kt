package joryu.sns_service.profile.controller

import joryu.sns_service.profile.dto.request.ProfileCreateRequest
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest
import joryu.sns_service.profile.dto.response.AllProfileResponse
import joryu.sns_service.profile.dto.response.ProfileInfoResponse
import joryu.sns_service.profile.entity.Profile
import joryu.sns_service.profile.service.ProfileService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
class ProfileApiController(
        private val profileService: ProfileService
) {

    @PostMapping()
    fun createProfile(@RequestBody profileCreateRequest: ProfileCreateRequest): ResponseEntity<ProfileInfoResponse> {
        val profileInfo = profileService.create(profileCreateRequest)
        return ResponseEntity(profileInfo, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findProfile(@PathVariable id: Long): ResponseEntity<ProfileInfoResponse> {
        val profileInfo = profileService.findOneById(id)
        return ResponseEntity(profileInfo, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateProfile(@PathVariable id: Long, @RequestBody profileUpdateRequest: ProfileUpdateRequest): ResponseEntity<ProfileInfoResponse> {
        val profileInfo = profileService.update(id, profileUpdateRequest)
        return ResponseEntity(profileInfo, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteProfile(@PathVariable id: Long): ResponseEntity<Any> {
        profileService.delete(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/follower/{id}")
    fun findAllFollowerProfiles(@PathVariable id: Long): ResponseEntity<AllProfileResponse> {
        val profiles = profileService.findAllFollowerProfileInfo(id)
        return ResponseEntity(profiles, HttpStatus.OK)
    }

    @GetMapping("/following/{id}")
    fun findAllFollowingProfiles(@PathVariable id: Long): ResponseEntity<AllProfileResponse> {
        val profiles = profileService.findAllFollowingProfileInfo(id)
        return ResponseEntity(profiles, HttpStatus.OK)
    }
}