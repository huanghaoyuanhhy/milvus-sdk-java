package io.milvus.v2.service.cdc.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CrossClusterTopology {
    private String sourceClusterId;
    private String targetClusterId;

    public io.milvus.grpc.CrossClusterTopology toGRPC() {
        return io.milvus.grpc.CrossClusterTopology.newBuilder()
                .setSourceClusterId(this.sourceClusterId)
                .setTargetClusterId(this.targetClusterId)
                .build();
    }
}
