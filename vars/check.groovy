def checkStage() {
    stage('Check services in kubernetes') {
        withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
            echo "Check service in kubernetes"

            def checkDeploy = sh (
                script: "kubectl get deploy"
                returnStdout: true
            ).trim()
            if(!checkPrcheckDeployometheus.contains("prometheus-deployment") &&!checkPrcheckDeployometheus.contains("pushgateway")){
                error("Services isn't in kuber")
            }
        }

    }
    echo "✅ Check complete"
}
return this