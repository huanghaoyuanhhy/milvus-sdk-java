package io.milvus.v2;

import io.milvus.client.MilvusClient;
import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.cdc.request.CrossClusterTopology;
import io.milvus.v2.service.cdc.request.MilvusCluster;
import io.milvus.v2.service.cdc.request.ReplicateConfiguration;
import io.milvus.v2.service.cdc.request.UpdateReplicateConfigurationReq;

import java.util.ArrayList;
import java.util.List;

public class CDCExample {
    private static final String clusterAURI = "http://192.168.1.93:19530";
    private static final String clusterBURI = "http://192.168.1.93:19500";

    private static final String clusterAId = "cdc-test-upstream";
    private static final String clusterBId = "cdc-test-downstream";

    private static final Integer pchannelNum = 16;

    private static List<String> generatePChannels(String clusterId) {
        List<String> pchannels = new ArrayList<>();
        for (int i = 0; i < pchannelNum; i++) {
            pchannels.add(clusterId + "-rootcoord-dml_" + i);
        }
        return pchannels;
    }

    public static void main(String[] args) {
        ConnectConfig clusterA = ConnectConfig.builder()
                .uri(clusterAURI)
                .build();
        MilvusClientV2 clusterAClient = new MilvusClientV2(clusterA);

        ConnectConfig clusterB = ConnectConfig.builder()
                .uri(clusterBURI)
                .build();
        MilvusClientV2 clusterBClient = new MilvusClientV2(clusterB);

        MilvusCluster milvusClusterA = MilvusCluster.builder()
                .clusterId(clusterAId)
                .uri(clusterAURI)
                .pchannels(generatePChannels(clusterAId))
                .build();
        MilvusCluster milvusClusterB = MilvusCluster.builder()
                .clusterId(clusterBId)
                .uri(clusterBURI)
                .pchannels(generatePChannels(clusterBId))
                .build();

        CrossClusterTopology topology = CrossClusterTopology.builder()
                .sourceClusterId(clusterAId)
                .targetClusterId(clusterBId)
                .build();

        ReplicateConfiguration configuration = ReplicateConfiguration.builder()
                .clusters(new ArrayList<MilvusCluster>(){{ add(milvusClusterA); add(milvusClusterB);}})
                .crossClusterTopologies(new ArrayList<CrossClusterTopology>(){{ add(topology); }} )
                .build();

        UpdateReplicateConfigurationReq updateReq = UpdateReplicateConfigurationReq.builder()
                .replicateConfiguration(configuration)
                .build();

        clusterAClient.updateReplicateConfiguration(updateReq);
        clusterBClient.updateReplicateConfiguration(updateReq);
    }
}
