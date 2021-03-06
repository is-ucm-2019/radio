package radio.core;

/**
 * Nivel de permisos que tiene un usuario de la aplicación.
 * Se le presentará una interfaz, y se le permitirá realizar distintas funciones,
 * dependiendo de sus permisos.
 * <p>
 * Un usuario sólo puede tener un rol particular en cualquier momento dado.
 *
 * @author Borja
 */
public enum UserPermission {
    ADMIN, PROGRAM_DIRECTOR, MUSIC_DIRECTOR, PR, ADVERTISING, IT;
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace('_', ' ');
    }
}
