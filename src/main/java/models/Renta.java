package models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "rentas")
public class Renta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    
    @Column(name = "fecha_renta")
    private LocalDate fechaRenta = LocalDate.now();
    
    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;
    
    @Column(name = "dias_renta")
    private int diasRenta = 14; 
    
    private BigDecimal multa;
    
    @Enumerated(EnumType.STRING)
    private EstadoRenta estado = EstadoRenta.ACTIVA;

    public Renta() {}
    
    public Renta(Usuario usuario, Libro libro ) {
		super();
		this.usuario = usuario;
		this.libro = libro;
		calcularFechaDevolucion();
		calcularMulta();
	}

	public void calcularMulta() {
        if (estado == EstadoRenta.ACTIVA && LocalDate.now().isAfter(fechaRenta.plusDays(diasRenta))) {
            long diasRetraso = ChronoUnit.DAYS.between(fechaRenta.plusDays(diasRenta), LocalDate.now());
            this.multa = BigDecimal.valueOf(diasRetraso * 5.50);
        }
    }
	
	public void calcularFechaDevolucion() {
	    if (fechaRenta != null && diasRenta > 0) {
	        this.fechaDevolucion = fechaRenta.plusDays(diasRenta);
	    }
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public LocalDate getFechaRenta() {
		return fechaRenta;
	}

	public void setFechaRenta(LocalDate fechaRenta) {
		this.fechaRenta = fechaRenta;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public int getDiasRenta() {
		return diasRenta;
	}

	public void setDiasRenta(int diasRenta) {
		this.diasRenta = diasRenta;
	}

	public BigDecimal getMulta() {
		return multa;
	}

	public void setMulta(BigDecimal multa) {
		this.multa = multa;
	}

	public EstadoRenta getEstado() {
		return estado;
	}

	public void setEstado(EstadoRenta estado) {
		this.estado = estado;
	}
	
}

enum EstadoRenta {
    ACTIVA, COMPLETADA, CON_MULTA
}