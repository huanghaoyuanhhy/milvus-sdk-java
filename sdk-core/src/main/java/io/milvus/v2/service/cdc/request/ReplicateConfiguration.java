package io.milvus.v2.service.cdc.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class ReplicateConfiguration {
    private List<MilvusCluster> clusters;
    private List<CrossClusterTopology> crossClusterTopologies;

    public io.milvus.grpc.ReplicateConfiguration toGRPC() {
        io.milvus.grpc.ReplicateConfiguration.Builder builder = io.milvus.grpc.ReplicateConfiguration.newBuilder();
        if (this.clusters != null) {
            for (MilvusCluster cluster : this.clusters) {
                builder.addClusters(cluster.toGRPC());
            }
        }

        if (this.crossClusterTopologies != null) {
            for (CrossClusterTopology topology : this.crossClusterTopologies) {
                builder.addCrossClusterTopology(topology.toGRPC());
            }
        }

        return builder.build();
    }
}
