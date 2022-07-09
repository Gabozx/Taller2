package dominio;

public class Usuario {

    private String nickname;
    private String nombre;
    private String correo;
    private int contacto;
    private String contrasena;
    private int cantCompras;
    private int cantVentas;
    
    public Usuario(String nickname, String nombre, String correo, int contacto, String contrasena) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.correo = correo;
        this.contacto = contacto;
        this.contrasena = contrasena;
        cantCompras = 0;
        cantVentas = 0;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getContacto() {
        return contacto;
    }
    public void setContacto(int contacto) {
        this.contacto = contacto;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public int getCantCompras() {
        return cantCompras;
    }
    public void setCantCompras(int cantCompras) {
        this.cantCompras = cantCompras;
    }
    public int getCantVentas() {
        return cantVentas;
    }
    public void setCantVentas(int cantVentas) {
        this.cantVentas = cantVentas;
    }
    public String toString() {
        return nickname + "," + nombre + "," + correo + "," + contacto + "," + contrasena;
    }
}