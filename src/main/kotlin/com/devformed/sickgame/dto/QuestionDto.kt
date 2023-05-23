package com.devformed.sickgame.dto

abstract class QuestionDto(val resourceType: ResourceType, val content: String)

class TextQuestionDto(content: String) : QuestionDto(ResourceType.TEXT, content)
class ImageQuestionDto(path: String) : QuestionDto(ResourceType.IMAGE, path)
class VideoQuestionDto(path: String) : QuestionDto(ResourceType.VIDEO, path)

enum class ResourceType {
    TEXT, IMAGE, VIDEO
}