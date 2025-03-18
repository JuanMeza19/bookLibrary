package models;

import jakarta.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(unique = true)
    private String isbn;
    
    @Column(name = "anio_publicacion")
    private Year anioPublicacion;
    
    @Enumerated(EnumType.STRING)
    private EstadoLibro estado = EstadoLibro.DISPONIBLE;
    
    @ManyToMany
    @JoinTable(
        name = "libro_autor",
        joinColumns = @JoinColumn(name = "libro_id"), 
        inverseJoinColumns = @JoinColumn(name = "autor_id") 
    )
    private List<Autor> autores = new ArrayList<>();
    
    @OneToMany(mappedBy = "libro")
    private List<Renta> rentas = new ArrayList<>();

    /**
     * Constructor de la clase Libro.
     * 
     * @param titulo          Título del libro.
     * @param isbn            Número de identificación ISBN del libro (único por libro).
     * @param anioPublicacion Año en que se publicó el libro (tipo Year).
     * @param estado          Estado actual del libro (DISPONIBLE, RENTADO, EN_REPARACION).
     * @param autor           Lista de autores que escribieron el libro (relación Many-to-Many).
     */
	public Libro(String titulo, String isbn, Year anioPublicacion, EstadoLibro estado, List<Autor> autors) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.anioPublicacion = anioPublicacion;
		this.estado = estado;
		this.autores = autors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Year getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(Year anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public EstadoLibro getEstado() {
		return estado;
	}

	public void setEstado(EstadoLibro estado) {
		this.estado = estado;
	}

	public List<Autor> getAutor() {
		return autores;
	}

	public void setAutor(List<Autor> autor) {
		this.autores = autor;
	}

	public List<Renta> getRentas() {
		return rentas;
	}

	public void setRentas(List<Renta> rentas) {
		this.rentas = rentas;
	}

	public enum EstadoLibro {
	    DISPONIBLE, RENTADO, EN_REPARACION
	}
	
	@Override
	public String toString() {
	    StringBuilder autoresStr = new StringBuilder();
	    for (Autor autor : autores) {
	        autoresStr.append(autor.getNombre()).append(", ");
	    }
	    
	    if (autoresStr.length() > 0) {
	        autoresStr.setLength(autoresStr.length() - 2);
	    }

	    return "Libro {" +
	           "ID=" + id +
	           ", Título='" + titulo + '\'' +
	           ", ISBN='" + isbn + '\'' +
	           ", Año de Publicación=" + anioPublicacion +
	           ", Estado=" + estado +
	           ", Autores=[" + autoresStr + "]" +
	           '}';
	}
    
}
