export class ApiError extends Error {
  success: boolean
  statusCode: number
  errorMessage: string
  constructor(success: boolean, statusCode: number, errorMessage: string) {
    super(errorMessage)
    this.success = success
    this.statusCode = statusCode
    this.errorMessage = errorMessage
  }
}
