package com.yaba.spring_security.messages;

public class ErrorMessage {
    public static final String REQUIRED_FIELD_NAME = "Ce champ est obligatore.";
    public static final String MALFORMED_FIELD = "Ce champ est mal formé.";
    public static final String ALREADY_EXISTS_FIELD = "La valeur %s existe deja.";
    public static final String NOT_FOUND_FIELD = "Aucun champ ne correspond avec le valeur %s.";
    public static final String MALFORMED_TOKEN = "Le token n'est pas valide.";
    public static final String EXPIRED_TOKEN = "Vos accréditations ont expirés. Veuillez vous reconnecter svp.";
    public static final String BAD_CREDENTIALS = "Les crédentiels fournis ne sont pas valides.";
    public static final String NOT_VALID_ACCOUNT = "Votre compte n'est pas actif. Veuillez contacter le support svp.";
    public static final String BAD_ROLE_REQUEST= "Le role %s n'est pas autorisé.";
    public static final String BAD_FIELD_REQUEST= "Le champ %s n'est pas correcte.";
    public static final String FIELD_REQUIRED = "Le champ %s est requis.";
    public static final String UNAUTHORIZED_ADMIN_ACCOUNT = "Désolé vous avez atteint la limite du nombre de comptes administrateurs autorisés.";
}
