package net.boomerangplatform.repository.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import net.boomerangplatform.repository.model.ArtifactSummary;
import net.boomerangplatform.repository.model.DependencyGraph;
import net.boomerangplatform.repository.model.SonarQubeReport;

@Service
public class RepositoryServiceImpl implements RepositoryService {
	
	private static final String PARAM_ARTIFACT_PATH = "{artifactPath}";
	
	private static final String PARAM_ARTIFACT_NAME = "{artifactName}";
	
	private static final String PARAM_ARTIFACT_VERSION = "{artifactVersion}";

  private static final String PARAM_ID = "{id}";

  private static final String PARAM_VERSION = "{version}";

  private static final Logger LOGGER = LogManager.getLogger();

  @Value("${repository.rest.url.base}")
  private String repositoryRestUrlBase;

  @Value("${repository.rest.url.dependencygraph}")
  private String repositoryRestUrlDependencygraph;

  @Value("${repository.rest.url.artifactsummary}")
  private String repositoryRestUrlArtifactsummary;

  @Value("${repository.rest.url.sonarqubereport}")
  private String repositoryRestUrlSonarqubereport;
  
  @Value("${repository.rest.url.sonarqubetestcoverage}")
  private String repositoryRestUrlSonarqubetestcoverage;
  
  @Value("${repository.rest.url.sonarqubetestcoveragedetail}")
  private String repositoryRestUrlSonarqubetestcoveragedetail;

  private RestTemplate restTemplate = new RestTemplate();

  @Override
  public DependencyGraph getDependencyGraph(String artifactPath, String artifactName,String artifactVersion) {

    final HttpHeaders headers = new HttpHeaders();
    final HttpEntity<String> request = new HttpEntity<>(headers);

    String url = repositoryRestUrlBase + repositoryRestUrlDependencygraph
    		.replace(PARAM_ARTIFACT_PATH, artifactPath).replace(PARAM_ARTIFACT_NAME, artifactName).replace(PARAM_ARTIFACT_VERSION, artifactVersion);

    LOGGER.info("getDependencyGraph() - url: " + url);

    DependencyGraph result = new DependencyGraph();
    try {
      final ResponseEntity<DependencyGraph> response =
          restTemplate.exchange(url, HttpMethod.GET, request, DependencyGraph.class);
      result = response.getBody() == null ? result : response.getBody();
    } catch (final RestClientException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return result;
  }

  @Override
  public ArtifactSummary getArtifactSummary(String artifactPath, String artifactName,String artifactVersion) {

    final HttpHeaders headers = new HttpHeaders();
    final HttpEntity<String> request = new HttpEntity<>(headers);

    String url = repositoryRestUrlBase + repositoryRestUrlArtifactsummary
        .replace(PARAM_ARTIFACT_PATH, artifactPath).replace(PARAM_ARTIFACT_NAME, artifactName).replace(PARAM_ARTIFACT_VERSION, artifactVersion);

    LOGGER.info("getArtifactSummary() - url: " + url);

    ArtifactSummary result = new ArtifactSummary();
    try {
      final ResponseEntity<ArtifactSummary> response =
          restTemplate.exchange(url, HttpMethod.GET, request, ArtifactSummary.class);
      result = response.getBody() == null ? result : response.getBody();
    } catch (final RestClientException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return result;
  }

  @Override
  public SonarQubeReport getSonarQubeReport(String id, String version) {

    final HttpHeaders headers = new HttpHeaders();
    final HttpEntity<String> request = new HttpEntity<>(headers);

    String url = repositoryRestUrlBase + repositoryRestUrlSonarqubereport
        .replace(PARAM_ID, id).replace(PARAM_VERSION, version);

    LOGGER.info("getSonarQubeReport() - url: " + url);

    SonarQubeReport result = new SonarQubeReport();
    try {
      final ResponseEntity<SonarQubeReport> response =
          restTemplate.exchange(url, HttpMethod.GET, request, SonarQubeReport.class);
      result = response.getBody();
    } catch (final RestClientException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return result;
  }
  
  @Override
  public SonarQubeReport getSonarQubeTestCoverage(String id, String version) {

    final HttpHeaders headers = new HttpHeaders();
    final HttpEntity<String> request = new HttpEntity<>(headers);

    String url = repositoryRestUrlBase + repositoryRestUrlSonarqubetestcoverage
        .replace(PARAM_ID, id).replace(PARAM_VERSION, version);

    LOGGER.info("getSonarQubeTestCoverage() - url: " + url);

    SonarQubeReport result = new SonarQubeReport();
    try {
      final ResponseEntity<SonarQubeReport> response =
          restTemplate.exchange(url, HttpMethod.GET, request, SonarQubeReport.class);
      result = response.getBody();
    } catch (final RestClientException e) {
      LOGGER.error(e.getMessage(), e);
    }

    return result;
  }  
}
