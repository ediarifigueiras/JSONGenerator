package pt.iscte.annotations

/**
 * Exclude Property
 * Annotation used to exclude a property from instantiation to JSON
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
annotation class ExcludeProperty

/**
 * Identifier
 * Annotation used to change the identifier of a value when it is instantiated to JSON
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
annotation class Identifier(val identifier: String)