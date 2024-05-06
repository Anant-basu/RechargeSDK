data class ApiResponse(
    val data: String,
    val responseCode: Int,
    val status: String,
    val token: String
)

data class Payload(
    val token: String,
    val ownerInfo: OwnerInfo,
    val tokenExpiry: Long,
    val allowedServices: List<Service>,
    val cnfId: Int,
    val redirectionInfo: RedirectionInfo,
    val isRedirect: Boolean
)

data class OwnerInfo(
    val firstName: String,
    val lastName: String,
    val mobileNumber: String,
    val emailId: String,
    val status: String,
    val distributorId: Int,
    val identificationId: Int,
    val ownerType: String
)

data class Service(
    val id: Int,
    val serviceName: String,
    val displayName: String,
    val ownerType: String,
    val ownerId: Int,
    val status: String
)

data class RedirectionInfo(
    val pushBackUrl: String
)
