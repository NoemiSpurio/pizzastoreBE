package it.prova.pizzastoreBE.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StatisticheDTO {

	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInizio;

	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFine;

	public StatisticheDTO() {
		super();
	}

	public StatisticheDTO(LocalDate dataInizio, LocalDate dataFine) {
		super();
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
}
