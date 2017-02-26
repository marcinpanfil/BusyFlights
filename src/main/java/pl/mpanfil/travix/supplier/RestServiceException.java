package pl.mpanfil.travix.supplier;

/**
 * Created by Marcin Panfil on 26.02.17.
 */
class RestServiceException extends Exception {

    RestServiceException(String message) {
        super(message);
    }
}