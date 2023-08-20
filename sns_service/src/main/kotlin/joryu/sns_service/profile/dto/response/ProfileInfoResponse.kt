package joryu.sns_service.profile.dto.response

import joryu.sns_service.profile.entity.Profile

class ProfileInfoResponse(val name: String) {
    constructor(profile: Profile): this(profile.name)
}