interface BroadcastRepository {

    fun getBroadcastContent() : BaseResult<String, String>
}