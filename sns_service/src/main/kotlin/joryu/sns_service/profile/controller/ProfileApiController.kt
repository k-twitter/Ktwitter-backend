package joryu.sns_service.profile.controller

import joryu.sns_service.profile.dto.request.ProfileCreateRequest
import joryu.sns_service.profile.dto.request.ProfileUpdateRequest
import joryu.sns_service.profile.dto.response.AllProfileResponse
import joryu.sns_service.profile.dto.response.ProfileInfoResponse
import joryu.sns_service.profile.service.ProfileService
import mu.KLogger
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profile")
class ProfileApiController(
        private val profileService: ProfileService,
) {
    val logger: KLogger = KotlinLogging.logger {}

    @PostMapping()
    fun createProfile(@RequestBody profileCreateRequest: ProfileCreateRequest): ResponseEntity<ProfileInfoResponse> {
        val profileInfo = profileService.create(profileCreateRequest)
        logger.info { "Hello World" }
        logger.error { "Error" }
        logger.warn { "Warn" }
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