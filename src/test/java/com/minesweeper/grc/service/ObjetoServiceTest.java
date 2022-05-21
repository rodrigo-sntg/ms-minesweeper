//package com.perinity.grc.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//import javax.inject.Inject;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.Response;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import com.perinity.grc.repository.ObjetoRepository;
//import com.perinity.grc.repository.entity.Objeto;
//import com.perinity.grc.repository.form.ObjetoForm;
//import com.perinity.grc.repository.form.PaginatorForm;
//
//import io.quarkus.hibernate.orm.panache.PanacheQuery;
//import io.quarkus.test.junit.QuarkusTest;
//import io.quarkus.test.junit.mockito.InjectMock;
//
//@QuarkusTest
//public class ObjetoServiceTest {
//
//	@Inject
//	ObjetoService service;
//	@InjectMock
//	ObjetoRepository repository;
//
//	PanacheQuery<Objeto> query;
//
//	private static final String NOME = "NomeAgendamento";
//	private static final String GRUPO = "GrupoAgendamento";
//
//	@SuppressWarnings("unchecked")
//	@BeforeEach
//	private void onBeforeEach(){
//		query = Mockito.mock(PanacheQuery.class);
//	}
//
//	private Objeto criarAgendamentoFuturo() {
//
//		Objeto agendamento = Objeto.builder()
//				.nome(NOME)
//				.grupo(GRUPO)
//				.dataAgendado(new Date())
//				.removido(false)
//				.build();
//		return agendamento;
//	}
//
//
//
//	@Test
//	public void testarListAllVazia() {
//		List<Objeto> listaMock = new ArrayList<Objeto>();
//
//		Mockito.when(query.page(Mockito.any()))
//				.thenReturn(query);
//		Mockito.when(query.list())
//				.thenReturn(listaMock);
//
//		Mockito.when(repository.pesquisarLista())
//				.thenReturn(query);
//
//		var resultado = service.pesquisarLista(criarPaginator());
//		assertTrue(CollectionUtils.isEmpty(resultado.getContents()));
//
//	}
//
//	@Test
//	public void testarListAll() {
//		List<Objeto> listaMock = new ArrayList<Objeto>();
//		listaMock.add(criarAgendamentoFuturo());
//
//		Mockito.when(query.page(Mockito.any()))
//				.thenReturn(query);
//		Mockito.when(query.list())
//				.thenReturn(listaMock);
//
//		Mockito.when(repository.pesquisarLista())
//				.thenReturn(query);
//
//		var resultado = service.pesquisarLista(criarPaginator());
//		assertTrue(resultado.getContents()
//				.size() == 1);
//
//		ObjetoDto agendamento = (ObjetoDto) resultado.getContents()
//				.iterator()
//				.next();
//		assertEquals(agendamento.getNome(), NOME);
//		assertEquals(agendamento.getGrupo(), GRUPO);
//
//	}
//
//	private PaginatorForm criarPaginator() {
//		PaginatorForm paginacao = PaginatorForm.builder()
//				.size(10)
//				.offset(10)
//				.build();
//		return paginacao;
//	}
//
//	@Test
//	public void testarListPendente() {
//		List<Objeto> listaMock = new ArrayList<Objeto>();
//		listaMock.add(criarAgendamentoFuturo());
//
//		Mockito.when(query.page(Mockito.any()))
//				.thenReturn(query);
//		Mockito.when(query.list())
//				.thenReturn(listaMock);
//
//		Mockito.when(repository.pesquisarListaPendente())
//				.thenReturn(query);
//
//		var resultado = service.pesquisarListaPendente(criarPaginator());
//		assertTrue(resultado.getContents()
//				.size() == 1);
//	}
//
//
//	@Test
//	public void testarCreate() {
//		ObjetoForm form = ObjetoForm.builder()
//				.nome(NOME)
//				.grupo(GRUPO)
//				.dataAgendado(LocalDate.now())
//				.build();
//		Objeto agendamento = service.convertToEntity(form);
//
//		when(repository.removerPorNomeEGrupo(NOME, GRUPO)).thenReturn(true);
//		when(repository.inserir(agendamento)).thenReturn(criarAgendamentoFuturo());
//
//		ObjetoDto inserido = service.inserir(form);
//
//		verify(repository, times(1)).removerPorNomeEGrupo(NOME, GRUPO);
//
//		assertEquals(inserido.getNome(), NOME);
//		assertEquals(inserido.getGrupo(), GRUPO);
//		assertFalse(inserido.isRemovido());
//	}
//
//	@Test
//	public void testarDelete() {
//		Objeto bean = criarAgendamentoFuturo();
//		when(repository.pesquisarPorNome(NOME)).thenReturn(bean);
//		Response response = service.removerPorNome(NOME);
//		verify(repository, times(1)).remover(bean);
//		assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());
//	}
//
//	@Test
//	public void testarDeleteNull() {
//		Objeto vazio = null;
//		when(repository.pesquisarPorNome(NOME)).thenReturn(vazio);
//		assertThrows(WebApplicationException.class, () -> service.removerPorNome(NOME));
//	}
//
//	@Test
//	public void testarConverterDto() {
//		Objeto entidade = criarAgendamentoFuturo();
//		ObjetoDto dto = service.convertToDto(entidade);
//
//		assertEquals(entidade.getId(), dto.getId());
//		assertEquals(entidade.getNome(), dto.getNome());
//		assertEquals(entidade.getGrupo(), dto.getGrupo());
//		assertEquals(Objects.nonNull(entidade.getDataAgendado()) ? entidade.getDataAgendado()
//				.toInstant()
//				.atZone(ZoneId.systemDefault())
//				.toLocalDate() : null, dto.getDataAgendado());
//		assertEquals(entidade.isRemovido(), dto.isRemovido());
//	}
//
//	@Test
//	public void testarConverterEntity() {
//		ObjetoForm form = criarAgendamentoFuturoForm();
//		Objeto entidade = service.convertToEntity(form);
//		assertEquals(entidade.getNome(), form.getNome());
//		assertEquals(entidade.getGrupo(), form.getGrupo());
//		assertEquals(Objects.nonNull(entidade.getDataAgendado()) ? entidade.getDataAgendado()
//				.toInstant()
//				.atZone(ZoneId.systemDefault())
//				.toLocalDate() : null, form.getDataAgendado());
//	}
//
//	private ObjetoForm criarAgendamentoFuturoForm() {
//		ObjetoForm agendamento = ObjetoForm.builder()
//				.nome(NOME)
//				.grupo(GRUPO)
//				.dataAgendado(LocalDate.now())
//				.build();
//		return agendamento;
//
//	}
//
//}
