package com.devformed.sickgame.dto

abstract class Question(val resourceType: ResourceType, val content: String)

class TextQuestion(content: String) : Question(ResourceType.TEXT, content)
class ImageQuestion(path: String) : Question(ResourceType.IMAGE, path)
class VideoQuestion(path: String) : Question(ResourceType.VIDEO, path)

enum class ResourceType {
    TEXT, IMAGE, VIDEO
}