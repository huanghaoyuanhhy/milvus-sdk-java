package io.milvus.v2.service.cdc.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class MilvusCluster {
    private String clusterId;
    private String uri;
    private String token;
    private List<String> pchannels;

    public io.milvus.grpc.MilvusCluster toGRPC() {
        io.milvus.grpc.ConnectionParam.Builder connectionParamBuilder = io.milvus.grpc.ConnectionParam.newBuilder()
                .setUri(this.uri);
        if (this.token != null) {
            connectionParamBuilder.setToken(this.token);
        }

        io.milvus.grpc.MilvusCluster.Builder builder = io.milvus.grpc.MilvusCluster.newBuilder()
                .setClusterId(this.clusterId)
                .setConnectionParam(connectionParamBuilder);
        if (this.pchannels != null) {
            builder.addAllPchannels(this.pchannels);
        }
        return builder.build();
    }
}
