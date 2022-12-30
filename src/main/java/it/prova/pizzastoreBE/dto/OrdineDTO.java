package it.prova.pizzastoreBE.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastoreBE.model.Ordine;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdineDTO {

	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	private Boolean closed;
	@NotBlank(message = "{codice.notblank}")
	private String codice;
	@NotNull(message = "{costo.notnull}")
	private Integer costo;

	private List<PizzaDTO> pizze;
	@NotNull(message = "{cliente.notnull}")
	private ClienteDTO cliente;
	@NotNull(message = "{fattorino.notnull}")
	private UtenteDTO fattorino;

	public OrdineDTO() {

	}

	public OrdineDTO(Long id, LocalDate data, Boolean closed, String codice, Integer costo, List<PizzaDTO> pizze,
			ClienteDTO cliente, UtenteDTO fattorino) {
		super();
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costo = costo;
		this.pizze = pizze;
		this.cliente = cliente;
		this.fattorino = fattorino;
	}

	public OrdineDTO(Long id, LocalDate data, Boolean closed, String codice, Integer costo) {
		super();
		this.id = id;
		this.data = data;
		this.closed = closed;
		this.codice = codice;
		this.costo = costo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public List<PizzaDTO> getPizze() {
		return pizze;
	}

	public void setPizze(List<PizzaDTO> pizze) {
		this.pizze = pizze;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public UtenteDTO getFattorino() {
		return fattorino;
	}

	public void setFattorino(UtenteDTO fattorino) {
		this.fattorino = fattorino;
	}

	public Ordine buildOrdineModel() {
		Ordine result = new Ordine(this.id, this.data, this.codice, this.costo, this.closed);

		if (this.pizze != null)
			result.setPizze(pizze.stream().map(PizzaDTO::buildPizzaModel).collect(Collectors.toList()));

		if (this.fattorino != null)
			result.setFattorino(this.fattorino.buildUtenteModel(false));

		if (this.cliente != null)
			result.setCliente(this.cliente.buildClienteModel());

		return result;
	}

	public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel, boolean includePizze, boolean includeFattorino,
			boolean includeCliente) {

		OrdineDTO result = new OrdineDTO(ordineModel.getId(), ordineModel.getData(), ordineModel.getClosed(),
				ordineModel.getCodice(), ordineModel.getCostoTotale());

		if (includePizze)
			result.setPizze(PizzaDTO.createPizzaDTOListFromModelList(ordineModel.getPizze()));
		if (includeFattorino)
			result.setFattorino(UtenteDTO.buildUtenteDTOFromModel(ordineModel.getFattorino()));
		if (includeCliente)
			result.setCliente(ClienteDTO.buildClienteDTOFromModel(ordineModel.getCliente()));

		return result;
	}

	public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput) {
		return modelListInput.stream().map(ordine -> {
			return OrdineDTO.buildOrdineDTOFromModel(ordine, false, false, false);
		}).collect(Collectors.toList());
	}

}
