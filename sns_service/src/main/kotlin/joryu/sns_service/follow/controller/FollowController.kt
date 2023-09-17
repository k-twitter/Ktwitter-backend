package joryu.sns_service.follow.controller

import joryu.sns_service.follow.dto.request.FollowRequest
import joryu.sns_service.follow.dto.request.UnFollowRequest
import joryu.sns_service.follow.service.FollowService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
class FollowController(
        private val followService: FollowService
) {
    @PostMapping("/{fromProfileId}")
    fun follow(@PathVariable("fromProfileId") fromProfileId: Long, @RequestBody followRequest: FollowRequest): ResponseEntity<Void> {
        followService.follow(fromProfileId, followRequest.toProfileId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{fromProfileId}")
    fun unFollow(@PathVariable("fromProfileId") fromProfileId: Long, @RequestBody unFollowRequest: UnFollowRequest): ResponseEntity<Void> {
        followService.unFollow(fromProfileId, unFollowRequest.toProfileId)
        return ResponseEntity.ok().build()
    }
}