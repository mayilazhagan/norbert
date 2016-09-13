package com.linkedin.norbert.javacompat.network

/**
 *
 * @author Dmytro Ivchenko
 */
class PartitionedNetworkClientFactory[PartitionedId](serviceName: String, zooKeeperConnectString: String,
                                                     zooKeeperSessionTimeoutMillis: Int,
                                                     closeChannelTimeMillis: Long, norbertOutlierMultiplier: Double, norbertOutlierConstant: Double,
                                                     partitionedLoadBalancerFactory: PartitionedLoadBalancerFactory[PartitionedId],
                                                     enableNorbertReroutingStrategies: Boolean = true)
{
  val config = new NetworkClientConfig

  config.setServiceName(serviceName);
  config.setZooKeeperConnectString(zooKeeperConnectString);
  config.setZooKeeperSessionTimeoutMillis(zooKeeperSessionTimeoutMillis);
  config.setCloseChannelTimeMillis(closeChannelTimeMillis);
  config.setOutlierMuliplier(norbertOutlierMultiplier);
  config.setOutlierConstant(norbertOutlierConstant);
  config.setEnableNorbertReroutingStrategies(enableNorbertReroutingStrategies);

  def createPartitionedNetworkClient(): PartitionedNetworkClient[PartitionedId] =
  {
    new NettyPartitionedNetworkClient[PartitionedId](config, partitionedLoadBalancerFactory);
  }
}
