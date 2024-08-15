package kh.pokedex.Data.Remote.Response

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)