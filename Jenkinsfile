@Library('pipelib@master') _

def THREADFIX_ID = env.THREADFIX_ID ? env.THREADFIX_ID : '115'

node {
  def mvn = tool 'M3'
  def root = pwd()

  stage('Setup') {
    git([
      url: env.GIT_URL ? env.GIT_URL : 'http://github.com/venicegeo/dg-pzsvc-hello-java',
      branch: "master"
    ])
  }

  stage('Archive') {
    sh """
      ${mvn}/bin/mvn clean package -U -Dmaven.repo.local=${root}
      cp target/dg-pzsvc-hello-java-1.0.0.jar ${root}/dg-pzsvc-hello-java.jar
    """
    // Removing mavenpush until we know where we are archiving to.
    //mavenPush()
  }
  stage('Staging Deploy') {
    cfPush()
    cfBgDeploy()
  }

  stage('Cleanup') {
    deleteDir()
  }
}
