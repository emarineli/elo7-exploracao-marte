package br.com.elo7.exploracao.teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.elo7.exploracao.teste.api.integracao.InstrucoesVeiculoExploracaoRestControllerWebIntegrationTest;
import br.com.elo7.exploracao.teste.api.integracao.TerrenoExploracaoRestControllerWebIntegrationTest;
import br.com.elo7.exploracao.teste.api.integracao.VeiculoExploracaoRecursoWebIntegrationTest;
import br.com.elo7.exploracao.teste.modelo.DirecaoCardealTest;
import br.com.elo7.exploracao.teste.modelo.PosicaoCartesianaTest;
import br.com.elo7.exploracao.teste.modelo.SondaTest;
import br.com.elo7.exploracao.teste.modelo.TerrenoExploracaoTest;
import br.com.elo7.exploracao.teste.repositorio.TerrenoExploracaoRepositorioTest;
import br.com.elo7.exploracao.teste.repositorio.VeiculoExploracaoRepositorioMemoriaTest;

/**
 * Suite de testes
 * 
 * @author emarineli
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  DirecaoCardealTest.class,
  PosicaoCartesianaTest.class,
  SondaTest.class,
  TerrenoExploracaoTest.class,
  TerrenoExploracaoRepositorioTest.class,
  VeiculoExploracaoRepositorioMemoriaTest.class,
  TerrenoExploracaoRestControllerWebIntegrationTest.class,
  VeiculoExploracaoRecursoWebIntegrationTest.class,
  InstrucoesVeiculoExploracaoRestControllerWebIntegrationTest.class
})
public class ExploracaoMarteTestSuite {
	/* NADA AQUI! */
}
