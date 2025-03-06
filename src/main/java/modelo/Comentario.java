package modelo;

import java.sql.Timestamp;

public class Comentario {
    private int id;
    private String contenido;
    private Timestamp fechaComentario;
    private String nombreUsuario;
    private int articuloId;
    
    public Comentario() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Timestamp getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(Timestamp fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(int articuloId) {
		this.articuloId = articuloId;
	}

    // Getters y Setters
    
    
    
}