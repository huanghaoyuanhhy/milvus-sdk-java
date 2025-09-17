package io.milvus.v2.service.cdc;

import io.milvus.grpc.MilvusServiceGrpc;
import io.milvus.grpc.Status;
import io.milvus.grpc.UpdateReplicateConfigurationRequest;
import io.milvus.v2.service.BaseService;
import io.milvus.v2.service.cdc.request.UpdateReplicateConfigurationReq;

public class CDCService extends BaseService {
    public Void updateReplicateConfiguration(MilvusServiceGrpc.MilvusServiceBlockingStub blockingStub, UpdateReplicateConfigurationReq requestParam) {
        UpdateReplicateConfigurationRequest request = UpdateReplicateConfigurationRequest.newBuilder()
                .setReplicateConfiguration(requestParam.getReplicateConfiguration().toGRPC())
                .build();

        String title = "UpdateReplicateConfiguration";

        Status response = blockingStub.updateReplicateConfiguration(request);
        rpcUtils.handleResponse(title, response);
        return null;
    }
}
