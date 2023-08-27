package joryu.sns_service.follower.controller

import joryu.sns_service.follower.dto.request.FollowRequest
import joryu.sns_service.follower.dto.request.UnFollowRequest
import joryu.sns_service.follower.dto.response.AllFollowingProfilesResponse
import joryu.sns_service.follower.entity.Follower
import joryu.sns_service.follower.service.FollowerService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
class FollowerController(
        private val followerService: FollowerService
) {
    @PostMapping("/{followerProfileId}")
    fun follow(@PathVariable("followerProfileId") followerProfileId: Long, @RequestBody followRequest: FollowRequest): ResponseEntity<Any> {
        followerService.followUp(followerProfileId, followRequest)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{followerProfileId}")
    fun unFollow(@PathVariable("followerProfileId") followerProfileId: Long, @RequestBody unFollowRequest: UnFollowRequest): ResponseEntity<Any> {
        followerService.unFollow(followerProfileId, unFollowRequest)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{followerProfileId}")
    fun findAllFollowersByProfileId(@PathVariable("followerProfileId") followerProfileId: Long): ResponseEntity<List<AllFollowingProfilesResponse?>> {
        return ResponseEntity.ok().body(followerService.getAllFollowingProfileByFollowerId(followerProfileId))
    }
}