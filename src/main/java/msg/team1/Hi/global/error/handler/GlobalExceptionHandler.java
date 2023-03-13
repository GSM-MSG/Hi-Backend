package msg.team1.Hi.global.error.handler;

import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.email.exception.AuthCodeExpiredException;
import msg.team1.Hi.domain.email.exception.ManyRequestEmailAuthException;
import msg.team1.Hi.domain.email.exception.MisMatchAuthCodeException;
import msg.team1.Hi.domain.email.exception.NotVerifyEmailException;
import msg.team1.Hi.domain.homebase.exception.AlreadyReservedException;
import msg.team1.Hi.domain.auth.exception.ExistEmailException;
import msg.team1.Hi.domain.auth.exception.MemberNotFoundException;
import msg.team1.Hi.domain.homebase.exception.FullHomeBaseReservationException;
import msg.team1.Hi.domain.member.exception.MisMatchPasswordException;
import msg.team1.Hi.domain.auth.exception.RefreshTokenNotFoundException;
import msg.team1.Hi.domain.notice.exception.NoticeNotFoundException;
import msg.team1.Hi.domain.reservation.exception.NotFoundReservationException;
import msg.team1.Hi.global.error.ErrorMessage;
import msg.team1.Hi.global.error.collection.TokenExpirationException;
import msg.team1.Hi.global.error.collection.TokenNotValidException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthCodeExpiredException.class)
    public ResponseEntity<ErrorMessage> handleAuthCodeExpiredException(HttpServletRequest request ,AuthCodeExpiredException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ManyRequestEmailAuthException.class)
    public ResponseEntity<ErrorMessage> handleManyRequestEmailAuthException(HttpServletRequest request , ManyRequestEmailAuthException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MisMatchAuthCodeException.class)
    public ResponseEntity<ErrorMessage> handleMisMatchAuthCodeException(HttpServletRequest request , MisMatchAuthCodeException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotVerifyEmailException.class)
    public ResponseEntity<ErrorMessage> handleNotVerifyEmailException(HttpServletRequest request , NotVerifyEmailException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ExistEmailException.class)
    public ResponseEntity<ErrorMessage> handleExistEmailException(HttpServletRequest request , ExistEmailException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleMemberNotFoundException(HttpServletRequest request , MemberNotFoundException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MisMatchPasswordException.class)
    public ResponseEntity<ErrorMessage> handleMisMatchPasswordException(HttpServletRequest request , MisMatchPasswordException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenExpirationException.class)
    public ResponseEntity<ErrorMessage> handleTokenExpirationException(HttpServletRequest request , TokenExpirationException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<ErrorMessage> handleTokenNotValidException(HttpServletRequest request , TokenNotValidException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoticeNotFoundException(HttpServletRequest request , NoticeNotFoundException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(AlreadyReservedException.class)
    public ResponseEntity<ErrorMessage> handleReservedHomeBaseException(HttpServletRequest request , AlreadyReservedException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleRefreshTokenNotFoundException(HttpServletRequest request , RefreshTokenNotFoundException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(FullHomeBaseReservationException.class)
    public ResponseEntity<ErrorMessage> handleFullHomeBaseReservationException(HttpServletRequest request , FullHomeBaseReservationException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(NotFoundReservationException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundReservationException(HttpServletRequest request , NotFoundReservationException e) {
        printError(request, e, e.getErrorCode().getMessage());
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getErrorCode().getStatus());
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }

    private void printError(HttpServletRequest request, RuntimeException ex, String message) {
        log.error(request.getRequestURI());
        log.error(message);
        ex.printStackTrace();
    }
}
