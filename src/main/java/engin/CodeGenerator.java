package engin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lancaster.dsl.SLO_DSL.Service;

import exceptions.InvalidTypeException;
import terraform.resources.Tags;
import terraform.resources.aws.AwsInstance;
import terraform.resources.aws.CreditSpecification;
import terraform.resources.aws.NetworkInterface;
import terraform.resources.aws.database.AwsDbInstance;
import terraform.resources.aws.vpcresources.AwsNetworkInterface;
import terraform.resources.aws.vpcresources.AwsSubnet;
import terraform.resources.aws.vpcresources.AwsVpc;
import terraform.resources.azure.OsProfile;
import terraform.resources.azure.OsProfileLinuxConfig;
import terraform.resources.azure.StorageImageReference;
import terraform.resources.azure.StorageOsDisk;
import terraform.resources.azure.base.AzurermResourceGroup;
import terraform.resources.azure.compute.AzurermVirtualMachine;
import terraform.resources.azure.database.AzurermPostgresqlDatabase;
import terraform.resources.azure.database.AzurermPostgresqlServer;
import terraform.resources.azure.database.Sku;
import terraform.resources.azure.database.StorageProfile;
import terraform.resources.azure.network.AzurermNetworkInterface;
import terraform.resources.azure.network.AzurermSubnet;
import terraform.resources.azure.network.AzurermVirtualNetwork;
import terraform.resources.azure.network.IpConfiguration;

public class CodeGenerator {

	public static String generateTerraFrom(Service instance) {
		try {
				String instanceName = instance.getComponentName();
				//File terraFile = new File(instanceName + ".tf");
				String appBlock = ""; 
				//BufferedWriter terraFileBw = new BufferedWriter(new FileWriter(terraFile));
				//System.out.println("Generating code for " + instance + "...");
				switch (instance.getName()) {
				case "aws_compute":
					AwsVpc aws_vpc = new AwsVpc(instanceName + "_aws_vpc");
					aws_vpc.setCidr_block("172.16.0.0/16");
					Tags vpcTags = new Tags();
					vpcTags.addTag("Name", "tf-example");
					aws_vpc.setTags(vpcTags);
					
					AwsSubnet aws_subnet = new AwsSubnet(instanceName + "_aws_subnet");
					aws_subnet.setVpc_id(aws_vpc.getIDReference());
					aws_subnet.setCidr_block("172.16.0.0/16");
					aws_subnet.setAvailability_zone("us-west-2a");
					Tags subNetTags = new Tags();
					subNetTags.addTag("Name", "tf-example");
					aws_subnet.setTags(subNetTags);
					
					AwsNetworkInterface aws_network_interface = new AwsNetworkInterface(instanceName + "_aws_network_interface");
					aws_network_interface.setSubnet_id(aws_subnet.getIDReference());
					ArrayList<String> private_ips = new ArrayList<>();
					private_ips.add("172.16.10.100");
					Tags interfaceTags = new Tags();
					interfaceTags.addTag("Name","primary_network_interface");
					aws_network_interface.setTags(interfaceTags);
					
					AwsInstance aws_instance = new AwsInstance(instanceName +"_aws_instance");
					aws_instance.setAmi("ami-22b9a343");
					aws_instance.setInstance_type(instance.getInstanceType());
					NetworkInterface network_interface = new NetworkInterface();
					network_interface.setNetwork_interface_id(aws_network_interface.getIDReference());
					network_interface.setDevice_index(0);
					aws_instance.setNetwork_interface(network_interface);
					CreditSpecification  credit_specification = new CreditSpecification();
					credit_specification.setCpu_credits("unlimited");
					aws_instance.setCredit_specification(credit_specification);					
					
					appBlock = aws_vpc.getBlock(0);
					appBlock += aws_subnet.getBlock(0);
					appBlock += aws_network_interface.getBlock(0);
					appBlock += aws_instance.getBlock(0);
					//System.out.println("Saving to " + instanceName + ".tf ...");				
					//terraFileBw.write(appBlock);
					//terraFileBw.close();
					//System.out.println("done :-)");
					break;
				case "aws_postgres_db":
					AwsDbInstance aws_db_instance = new AwsDbInstance(instanceName + "_aws_db_instance");
					aws_db_instance.setAllocated_storage("20");
					aws_db_instance.setStorage_type("gp2");
					aws_db_instance.setEngine("postgres");
					aws_db_instance.setEngin_version("5.7");
					aws_db_instance.setInstance_class("db.t2.micro");
					aws_db_instance.setName("mydb");
					aws_db_instance.setUsername("");
					aws_db_instance.setPassword("");
					appBlock = aws_db_instance.getBlock(0);
					break;
				case "azure_compute":
					AzurermResourceGroup azurerm_resource_group = new AzurermResourceGroup(instanceName + "_resource_group");
					azurerm_resource_group.setName(instanceName + "_resource_group");
					azurerm_resource_group.setLocation(instance.getRegion());
					
					AzurermVirtualNetwork azurerm_virtual_network = new AzurermVirtualNetwork(instanceName + "_network");
					azurerm_virtual_network.setResource_group_name(azurerm_resource_group.getName());
					ArrayList<String> addressSpace = new ArrayList<>();
					addressSpace.add("10.0.0.0/16");
					azurerm_virtual_network.setAddress_space(addressSpace);
					azurerm_virtual_network.setLocation(azurerm_resource_group.getLocation());
					azurerm_virtual_network.setName(instanceName + "_network");
					
					AzurermSubnet azurerm_subnet = new AzurermSubnet(instanceName + "_subnet");
					azurerm_subnet.setName(instanceName + "_subnet");
					azurerm_subnet.setResource_group_name(azurerm_resource_group.getName()); 
					azurerm_subnet.setVirtual_network_name(azurerm_virtual_network.getName());
					azurerm_subnet.setAddress_prefix("10.0.2.0/24");
					
					AzurermNetworkInterface azurerm_network_interface = new AzurermNetworkInterface(instanceName + "_nic");
					azurerm_network_interface.setName(instanceName + "_nic");
					azurerm_network_interface.setResource_group_name(azurerm_resource_group.getName());
					azurerm_network_interface.setLocation(azurerm_resource_group.getLocation());
					IpConfiguration ip_configuration = new IpConfiguration();
					ip_configuration.setName(instanceName + "_ip_configuration");
					ip_configuration.setPrivate_ip_address_allocation("Dynamic");
					ip_configuration.setSubnet_id(azurerm_subnet.getIDReference());
					ArrayList<IpConfiguration> ip_configurations = new ArrayList<>();
					ip_configurations.add(ip_configuration);
					azurerm_network_interface.setIp_configuration(ip_configurations);
					
					AzurermVirtualMachine azurermVM = new AzurermVirtualMachine(instanceName + "_vm");
					azurermVM.setName(instanceName + "_vm");
					azurermVM.setResource_group_name(azurerm_resource_group.getName());
					azurermVM.setLocation(azurerm_resource_group.getLocation());
					ArrayList<String> network_interface_ids = new ArrayList<>();
					network_interface_ids.add(azurerm_network_interface.getIDReference());
					azurermVM.setNetwork_interface_ids(network_interface_ids);
					azurermVM.setVm_size(instance.getInstanceType());
					StorageImageReference storage_image_reference = new StorageImageReference();
					storage_image_reference.provisionFromAzurePlatformImage("Canonical", "UbuntuServer", "16.04-LTS");
					storage_image_reference.setVersion("latest");
					azurermVM.setStorage_image_reference(storage_image_reference);
					StorageOsDisk storage_os_disk = new StorageOsDisk();
					storage_os_disk.setName(instanceName + "_storage_os_disk");
					storage_os_disk.setCaching("ReadWrite");
					storage_os_disk.setCreate_option("FromImage");
					storage_os_disk.setManaged_disk_type("Standard_LRS");
					azurermVM.setStorage_os_disk(storage_os_disk);
					OsProfile os_profile = new OsProfile();
					os_profile.setComputer_name(instanceName + "_hostname");
					os_profile.setAdmin_username("");
					os_profile.setAdmin_password("");
					azurermVM.setOs_profile(os_profile);
					OsProfileLinuxConfig os_profile_linux_config = new OsProfileLinuxConfig();
					os_profile_linux_config.setDisable_password_authentication(false);
					azurermVM.setOs_profile_linux_config(os_profile_linux_config);
					Tags tags = new Tags();
					tags.addTag("environment", "staging");
					azurermVM.setTags(tags);
					
					appBlock = azurerm_resource_group.getBlock(0);
					appBlock += azurerm_virtual_network.getBlock(0);
					appBlock += azurerm_subnet.getBlock(0);
					appBlock += azurerm_network_interface.getBlock(0);
					appBlock += azurerm_virtual_network.getBlock(0);
					appBlock += azurermVM.getBlock(0);
					//System.out.println("Saving to " + instanceName + ".tf ...");					
					//terraFileBw.write(appBlock);
					//terraFileBw.close();
					//System.out.println("done :-)");
					break;
				case "azure_postgres_db":
					AzurermResourceGroup  azurerm_resource_group2 = new AzurermResourceGroup(instanceName +"_azurerm_resource_group"); 
					azurerm_resource_group2.setName("api-rg-pro");
					azurerm_resource_group2.setLocation(instance.getRegion());
					AzurermPostgresqlServer azurerm_postgresql_server = new AzurermPostgresqlServer(instanceName + "_azurerm_postgresql_server");
					azurerm_postgresql_server.setName("postgresql-server-1");
					azurerm_postgresql_server.setLocation(instance.getRegion());
					azurerm_postgresql_server.setResource_group_name(azurerm_resource_group2.getName());
					azurerm_postgresql_server.setAdministrator_login("psqladminun");
					azurerm_postgresql_server.setAdministrator_login_password("H@Sh1CoR3!");
					azurerm_postgresql_server.setVersion("9.5");
					azurerm_postgresql_server.setSsl_enforcement("Enabled");
					Sku sku = new Sku();  
					sku.setName("B_Gen5_2");
					sku.setCapacity(2);
					sku.setTier("Basic");
					sku.setFamily("Gen5");
					azurerm_postgresql_server.setSku(sku);
					StorageProfile storage_profile = new StorageProfile();
					storage_profile.setStorage_mb(5120);
					storage_profile.setBackup_retention_days(7);
					storage_profile.setGeo_redundant_backup("Disabled");
   				    azurerm_postgresql_server.setStorage_profile(storage_profile);
   				    AzurermPostgresqlDatabase azurerm_postgresql_database = new AzurermPostgresqlDatabase(instanceName + "_azurerm_postgresql_database");
   				    azurerm_postgresql_database.setName("exampledb");
   				    azurerm_postgresql_database.setResource_group_name("${azurerm_resource_group.test.name}");
   				    azurerm_postgresql_database.setServer_name("${azurerm_postgresql_server.test.name}");
   				    azurerm_postgresql_database.setCharset("UTF8");
   				    azurerm_postgresql_database.setCollation("English_United States.1252");
   				    
   				    appBlock = azurerm_resource_group2.getBlock(0);
   				    appBlock += azurerm_postgresql_server.getBlock(0);
   				    appBlock += azurerm_postgresql_database.getBlock(0);
					break;
				}
				return appBlock;
		} catch (InvalidTypeException e) {
			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		}
		return "Terraform Code Generation Not Supported Yet";
	}
}
