package io.milvus.v2.service.cdc.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpdateReplicateConfigurationReq {
    private ReplicateConfiguration replicateConfiguration;

}
