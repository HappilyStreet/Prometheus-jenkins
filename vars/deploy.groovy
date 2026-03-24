def deployStage() {
    stage('Deploy prometheus to host') {
        dir(serviceDir) {
            withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                withEnv(["PATH=/usr/local/bin:/opt/homebrew/bin:$PATH"]) {
                    echo "KUBECONFIG path is: ${env.KUBECONFIG}"
                    sh "kubectl get nodes --kubeconfig ${env.KUBECONFIG}"

                    sh "pwd"
                    
                    echo "Deploying to Kubernetes using Helm..."
                    sh "Helm upgrade --install prometheus-release ./Prometheus-Helm"

                    sh "Helm upgrade --install pushgateway-release ./Pushgateway"
                }
            }
        }
    }
    echo "✅ Deploy complete"
}
return this