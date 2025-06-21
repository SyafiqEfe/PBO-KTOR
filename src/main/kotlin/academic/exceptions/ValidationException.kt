package academic.exceptions

class ValidationException(val errors: Map<String, String>) : RuntimeException(errors.toString())
