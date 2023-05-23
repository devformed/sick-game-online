package com.devformed.sickgame.dto

abstract class QuestionDto {
    abstract val resourceType: ResourceType
    abstract val content: String
}

data class TextQuestionDto(override val content: String) : QuestionDto() {
    override val resourceType: ResourceType = ResourceType.TEXT
}

data class ImageQuestionDto(override val content: String) : QuestionDto() {
    override val resourceType: ResourceType = ResourceType.IMAGE
}

data class VideoQuestionDto(override val content: String) : QuestionDto() {
    override val resourceType: ResourceType = ResourceType.VIDEO
}