package com.koowin.multiplayer.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "Movement")
public class Movement {
  @DynamoDBHashKey(attributeName = "gameId")
  private Long gameId;

  @DynamoDBAttribute
  @DynamoDBIndexHashKey(attributeName = "turnCount")
  private Long turnCount;
}
