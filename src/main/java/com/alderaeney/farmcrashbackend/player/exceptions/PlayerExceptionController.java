package com.alderaeney.farmcrashbackend.player.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlayerExceptionController {
    @ExceptionHandler(value = PlayerNotFoundException.class)
    public ResponseEntity<Object> playerNotFoundException(PlayerNotFoundException exception) {
        return new ResponseEntity<>("Player with id " + exception.id + " was not found in the database",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotEnoughMoneyException.class)
    public ResponseEntity<Object> notEnoughMoneyException(NotEnoughMoneyException exception) {
        return new ResponseEntity<>("Not enough money to buy the crops, available money: " + exception.availableMoney
                + ", price to buy the crops: " + exception.price, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameTakenException.class)
    public ResponseEntity<Object> usernameTakenException(UsernameTakenException exception) {
        return new ResponseEntity<>("Username already taken: " + exception.name, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = WorkerNotFoundInPlayerException.class)
    public ResponseEntity<Object> workerNotFoundInPlayerException(WorkerNotFoundInPlayerException exception) {
        return new ResponseEntity<>("Worker on index " + exception.index + " not found in player.",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CropNotFoundInPlayerException.class)
    public ResponseEntity<Object> cropNotFoundInPlayerException(CropNotFoundInPlayerException exception) {
        return new ResponseEntity<>("Crop on index " + exception.index + " not found in player.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = WorkerAlreadyHiredException.class)
    public ResponseEntity<Object> workerAlreadyHiredException(WorkerAlreadyHiredException exception) {
        return new ResponseEntity<>("Worker with id " + exception.id + " already hired.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PlayerByUSernameNotFoundException.class)
    public ResponseEntity<Object> playerByUsernameNotFoundException(PlayerByUSernameNotFoundException exception) {
        return new ResponseEntity<>("Player with username " + exception.username + " was not found in the database",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> indexOutOfBoundsException(IndexOutOfBoundsException exception) {
        return new ResponseEntity<>("Index " + exception.index + " is out of bounds", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotEnoughMoneyToHireException.class)
    public ResponseEntity<Object> notEnoughMoneyToHireException(NotEnoughMoneyToHireException exception) {
        return new ResponseEntity<>("Not enough money to hire worker, available money: " + exception.money
                + ", cost: " + exception.cost, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotEnoughMoneyToPerformTaskException.class)
    public ResponseEntity<Object> notEnoughMoneyToPerformTaskException(NotEnoughMoneyToPerformTaskException exception) {
        return new ResponseEntity<>("Not enough money to perform task, available money: " + exception.money
                + ", cost: " + exception.cost, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordsDoNotMatchException.class)
    public ResponseEntity<Object> passwordsDoNotMatchException(PasswordsDoNotMatchException exception) {
        return new ResponseEntity<>(exception.msg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CannotStoreImageException.class)
    public ResponseEntity<Object> cannotStoreImageException(CannotStoreImageException exception) {
        return new ResponseEntity<>(exception.msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ImageTooBigException.class)
    public ResponseEntity<Object> imageTooBigException(ImageTooBigException exception) {
        return new ResponseEntity<>(exception.msg, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ImageTypeNotSupported.class)
    public ResponseEntity<Object> imageTypeNotSupportedException(ImageTypeNotSupported exception) {
        return new ResponseEntity<>(exception.msg, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = OldPasswordDoesNotMatchException.class)
    public ResponseEntity<Object> oldPasswordDoesNotMatchException(OldPasswordDoesNotMatchException exception) {
        return new ResponseEntity<>(exception.msg, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = NoFriendRequestFound.class)
    public ResponseEntity<Object> noFriendRequestFound(NoFriendRequestFound exception) {
        return new ResponseEntity<>("No request found between " + exception.senderName + " and " + exception.getterName,
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = FriendRequestToSamePlayerException.class)
    public ResponseEntity<Object> friendRequestToSamePlayerException(FriendRequestToSamePlayerException exception) {
        return new ResponseEntity<>("Friend request sent to the same player " + exception.username,
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = PlayerNotBlockedException.class)
    public ResponseEntity<Object> playerNotBlockedException(PlayerNotBlockedException exception) {
        return new ResponseEntity<>("Player with username " + exception.username + " not blocked.",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PlayerAlreadyBlockedException.class)
    public ResponseEntity<Object> playerAlreadyBlockedException(PlayerAlreadyBlockedException exception) {
        return new ResponseEntity<>("Player with username " + exception.username + " already blocked.",
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = PlayerBlockingItselfException.class)
    public ResponseEntity<Object> playerBlockingItselfException(PlayerBlockingItselfException exception) {
        return new ResponseEntity<>(exception.msg, HttpStatus.NOT_ACCEPTABLE);
    }
}
