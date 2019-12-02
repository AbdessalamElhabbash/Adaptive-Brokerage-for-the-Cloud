package terraform.resources.azure.compute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidTypeException;
import exceptions.RequiredArgumentException;
import terraform.provider.Constants;
import terraform.resources.Resource;
import terraform.resources.Tags;
import terraform.resources.azure.BootDiagnostics;
import terraform.resources.azure.Identity;
import terraform.resources.azure.OsProfile;
import terraform.resources.azure.OsProfileLinuxConfig;
import terraform.resources.azure.OsProfileSecrets;
import terraform.resources.azure.OsProfileWindowsConfig;
import terraform.resources.azure.Plan;
import terraform.resources.azure.StorageDataDisk;
import terraform.resources.azure.StorageImageReference;
import terraform.resources.azure.StorageOsDisk;
import terraform.resources.azure.VaultCertificates;
import terraform.resources.azure.Winrm;

public class AzurermVirtualMachine extends Resource {

	public AzurermVirtualMachine(String resourceName) {
		super(resourceName);
	}

	private String name; // required
	private String resource_group_name; // required
	private String location; // required
	private ArrayList<String> network_interface_ids; // required
	private OsProfileLinuxConfig os_profile_linux_config; // required
	private OsProfileWindowsConfig os_profile_windows_config; // required
	private String vm_size; // required
	private String availability_set_id;
	private BootDiagnostics boot_diagnostics;
	private boolean delete_os_disk_on_termination; // default false
	private boolean delete_data_disks_on_termination; // default false
	private Identity identity;
	private String license_type; // Possible values are Windows_Client and
									// Windows_Server.
	private OsProfile os_profile; // Required when create_option in the
									// storage_os_disk block is set to
									// FromImage.
	private ArrayList<OsProfileSecrets> os_profile_secrets;
	private Plan plan;
	private String primary_network_interface_id;
	private ArrayList<StorageDataDisk> storage_data_disk;
	private StorageImageReference storage_image_reference;
	private StorageOsDisk storage_os_disk; // required
	private Tags tags;
	private ArrayList<String> zones;
	private static final Set<String> LICENSE_TYPES;

	static {
		LICENSE_TYPES = new HashSet<String>();
		LICENSE_TYPES.add("Windows_Client");
		LICENSE_TYPES.add("Windows_Server");
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the resource_group_name
	 */
	public String getResource_group_name() {
		return resource_group_name;
	}

	/**
	 * @param resource_group_name
	 *            the resource_group_name to set
	 */
	public void setResource_group_name(String resource_group_name) {
		this.resource_group_name = resource_group_name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the network_interface_ids
	 */
	public ArrayList<String> getNetwork_interface_ids() {
		return network_interface_ids;
	}

	/**
	 * @param network_interface_ids
	 *            the network_interface_ids to set
	 */
	public void setNetwork_interface_ids(ArrayList<String> network_interface_ids) {
		this.network_interface_ids = network_interface_ids;
	}

	/**
	 * @return the os_profile_linux_config
	 */
	public OsProfileLinuxConfig getOs_profile_linux_config() {
		return os_profile_linux_config;
	}

	/**
	 * @param os_profile_linux_config
	 *            the os_profile_linux_config to set
	 */
	public void setOs_profile_linux_config(OsProfileLinuxConfig os_profile_linux_config) {
		this.os_profile_linux_config = os_profile_linux_config;
	}

	/**
	 * @return the os_profile_windows_config
	 */
	public OsProfileWindowsConfig getOs_profile_windows_config() {
		return os_profile_windows_config;
	}

	/**
	 * @param os_profile_windows_config
	 *            the os_profile_windows_config to set
	 */
	public void setOs_profile_windows_config(OsProfileWindowsConfig os_profile_windows_config) {
		this.os_profile_windows_config = os_profile_windows_config;
	}

	/**
	 * @return the vm_size
	 */
	public String getVm_size() {
		return vm_size;
	}

	/**
	 * @param vm_size
	 *            the vm_size to set
	 */
	public void setVm_size(String vm_size) {
		this.vm_size = vm_size;
	}

	/**
	 * @return the availability_set_id
	 */
	public String getAvailability_set_id() {
		return availability_set_id;
	}

	/**
	 * @param availability_set_id
	 *            the availability_set_id to set
	 */
	public void setAvailability_set_id(String availability_set_id) {
		this.availability_set_id = availability_set_id;
	}

	/**
	 * @return the boot_diagnostics
	 */
	public BootDiagnostics getBoot_diagnostics() {
		return boot_diagnostics;
	}

	/**
	 * @param boot_diagnostics
	 *            the boot_diagnostics to set
	 */
	public void setBoot_diagnostics(BootDiagnostics boot_diagnostics) {
		this.boot_diagnostics = boot_diagnostics;
	}

	/**
	 * @return the delete_os_disk_on_termination
	 */
	public boolean isDelete_os_disk_on_termination() {
		return delete_os_disk_on_termination;
	}

	/**
	 * @param delete_os_disk_on_termination
	 *            the delete_os_disk_on_termination to set
	 */
	public void setDelete_os_disk_on_termination(boolean delete_os_disk_on_termination) {
		this.delete_os_disk_on_termination = delete_os_disk_on_termination;
	}

	/**
	 * @return the delete_data_disks_on_termination
	 */
	public boolean isDelete_data_disks_on_termination() {
		return delete_data_disks_on_termination;
	}

	/**
	 * @param delete_data_disks_on_termination
	 *            the delete_data_disks_on_termination to set
	 */
	public void setDelete_data_disks_on_termination(boolean delete_data_disks_on_termination) {
		this.delete_data_disks_on_termination = delete_data_disks_on_termination;
	}

	/**
	 * @return the identity
	 */
	public Identity getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	/**
	 * @return the license_type
	 */
	public String getLicense_type() {
		return license_type;
	}

	/**
	 * @param license_type
	 *            the license_type to set
	 */
	public void setLicense_type(String license_type) throws InvalidTypeException {
		if (LICENSE_TYPES.contains(license_type)) {
			this.license_type = license_type;
		} else {
			throw new InvalidTypeException("Possible values are Windows_Client and Windows_Server.");
		}
	}

	/**
	 * @return the os_profile
	 */
	public OsProfile getOs_profile() {
		return os_profile;
	}

	/**
	 * @param os_profile
	 *            the os_profile to set
	 */
	public void setOs_profile(OsProfile os_profile) {
		this.os_profile = os_profile;
	}

	/**
	 * @return the os_profile_secrets
	 */
	public ArrayList<OsProfileSecrets> getOs_profile_secrets() {
		return os_profile_secrets;
	}

	/**
	 * @param os_profile_secrets
	 *            the os_profile_secrets to set
	 */
	public void setOs_profile_secrets(ArrayList<OsProfileSecrets> os_profile_secrets) {
		this.os_profile_secrets = os_profile_secrets;
	}

	/**
	 * @return the plan
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * @param plan
	 *            the plan to set
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	/**
	 * @return the primary_network_interface_id
	 */
	public String getPrimary_network_interface_id() {
		return primary_network_interface_id;
	}

	/**
	 * @param primary_network_interface_id
	 *            the primary_network_interface_id to set
	 */
	public void setPrimary_network_interface_id(String primary_network_interface_id) {
		this.primary_network_interface_id = primary_network_interface_id;
	}

	/**
	 * @return the storage_data_disk
	 */
	public ArrayList<StorageDataDisk> getStorage_data_disk() {
		return storage_data_disk;
	}

	/**
	 * @param storage_data_disk
	 *            the storage_data_disk to set
	 */
	public void setStorage_data_disk(ArrayList<StorageDataDisk> storage_data_disk) {
		this.storage_data_disk = storage_data_disk;
	}

	/**
	 * @return the storage_image_reference
	 */
	public StorageImageReference getStorage_image_reference() {
		return storage_image_reference;
	}

	/**
	 * @param storage_image_reference
	 *            the storage_image_reference to set
	 */
	public void setStorage_image_reference(StorageImageReference storage_image_reference) {
		this.storage_image_reference = storage_image_reference;
	}

	/**
	 * @return the storage_os_disk
	 */
	public StorageOsDisk getStorage_os_disk() {
		return storage_os_disk;
	}

	/**
	 * @param storage_os_disk
	 *            the storage_os_disk to set
	 */
	public void setStorage_os_disk(StorageOsDisk storage_os_disk) {
		this.storage_os_disk = storage_os_disk;
	}

	/**
	 * @return the tags
	 */
	public Tags getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(Tags tags) {
		this.tags = tags;
	}

	/**
	 * @return the zones
	 */
	public ArrayList<String> getZones() {
		return zones;
	}

	/**
	 * @param zones
	 *            the zones to set
	 */
	public void setZones(ArrayList<String> zones) {
		this.zones = zones;
	}

	/**
	 * Checks wether the required parameters are set and the consistency
	 * conditions are met
	 * 
	 * @return
	 */
	private boolean isValid() throws RequiredArgumentException {
		if (name == null || name.equals("")) {
			throw new RequiredArgumentException("name conifig is required for the azurerm_virtual_machine object");
		}
		if (resource_group_name == null || resource_group_name.equals("")) {
			throw new RequiredArgumentException(
					"resource_group_name conifig is required for the azurerm_virtual_machine object");
		}
		if (location == null || location.equals("")) {
			throw new RequiredArgumentException("location conifig is required for the azurerm_virtual_machine object");
		}
		if (network_interface_ids == null || network_interface_ids.isEmpty()) {
			throw new RequiredArgumentException(
					"network_interface_ids conifig is required for the azurerm_virtual_machine object");
		}
		if (os_profile_linux_config == null && os_profile_windows_config == null) {
			throw new RequiredArgumentException(
					"One of os_profile_linux_config or os_profile_linux_config conifig is required for the azurerm_virtual_machine object");
		}
		if (vm_size == null || vm_size.equals("")) {
			throw new RequiredArgumentException("vm_size conifig is required for the azurerm_virtual_machine object");
		}
		if (storage_os_disk == null) {
			throw new RequiredArgumentException(
					"storage_os_disk conifig is required for the azurerm_virtual_machine object");
		}
		if (storage_os_disk.getCreate_option().equals("\"FromImage\"") && os_profile == null) {
			throw new RequiredArgumentException(
					"os_profile is required for the azurerm_virtual_machine object when create_option in the storage_os_disk block is set to FromImage");
		}

		/*
		 * winrm certificate_url must also be specified in the
		 * vault_certificates block within the os_profile_secrets block.
		 */
		if (os_profile_secrets != null) {
			boolean winrmCertUul = false;
			ArrayList<Winrm> winrms = os_profile_windows_config.getWinrm();
			for (OsProfileSecrets osSecret : os_profile_secrets) {
				for (VaultCertificates vaultCertificate : osSecret.getVault_certificates()) {
					for (Winrm win : winrms) {
						if (win.getCertificate_url().equals(vaultCertificate.getCertificate_url())) {
							winrmCertUul = true;
						}
					}
				}
			}
			if (!winrmCertUul) {
				throw new RequiredArgumentException(
						"winrm certificate_url must also be specified in the vault_certificates block within the os_profile_secrets block for the azurerm_virtual_machine object");
			}
		}

		/*
		 * lun the logical unit number of the data disk needs to be unique
		 * within all the Data Disks on the Virtual Machine.
		 */
		if (storage_data_disk != null) {
			for (StorageDataDisk dataDisk : storage_data_disk) {
				String lun = dataDisk.getLun();
				for (StorageDataDisk dataDisk2 : storage_data_disk) {
					if (dataDisk != dataDisk2) {
						if (lun.equals(dataDisk2.getLun())) {
							throw new RequiredArgumentException("The logical unit number (lun) of the data disk needs to be unique within all the Data Disks on the Virtual Machine.");
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public String getBlock(int level) {
		/***for formatting the terraform code**/
		String headIndent="";		
		for(int i=0; i<level; i++){
			headIndent += Constants.indent;
		}
		String elementIndent= headIndent + Constants.indent;
		/*******************************************************/
		
		try {
			isValid();
		} catch (RequiredArgumentException e) {
			e.printStackTrace();
		}
		
		String block = headIndent+ "resource \"azurerm_virtual_machine\" \"" + resourceName + "\"  { \n";

		block += elementIndent + "name = \"" + name + "\"\n";
		block += elementIndent + "resource_group_name = \"" + resource_group_name + "\"\n";
		block += elementIndent + "location = \"" + location + "\"\n";		
		block += elementIndent + "network_interface_ids = " + appendDoubleQuotes(network_interface_ids) + "\n";
		block += elementIndent + "vm_size = \"" + vm_size + "\"\n";

		if(os_profile_linux_config != null){
			block += elementIndent + os_profile_linux_config.getBlock(level+1);
		}
		if(os_profile_windows_config != null){
			block += os_profile_windows_config.getBlock(level+1);
		}

		if (availability_set_id != null && !vm_size.equals("")) {
			block += elementIndent + "availability_set_id = \"" + availability_set_id + "\"\n";
		}
		if (boot_diagnostics != null) {
			block += os_profile_windows_config.getBlock(level+ 1);
			block += elementIndent + "delete_os_disk_on_termination = \""
					+ delete_os_disk_on_termination + "\"\n";
		}
		if (delete_data_disks_on_termination) {
			block += elementIndent + "delete_data_disks_on_termination = \""
					+ delete_data_disks_on_termination + "\"\n";
		}
		if (identity != null) {
			block += identity.getBlock(level+1);
		}
		if (license_type != null && !license_type.equals("")) {
			block += elementIndent + "license_type = \"" + license_type + "\"\n";
		}
		if (os_profile != null) {
			block += os_profile.getBlock(level+1);
		}

		if (os_profile_secrets != null) {
			for (OsProfileSecrets os_profile_secret : os_profile_secrets) {
				block += os_profile_secret.getBlock(level+1);
			}
		}
		if (plan != null) {
			block += plan.getBlock(level+1);
		}
		if (primary_network_interface_id != null && !primary_network_interface_id.equals("")) {
			block += elementIndent + "primary_network_interface_id = \""
					+ primary_network_interface_id + "\"\n";
		}
		if (storage_data_disk != null) {
			for (StorageDataDisk dataDisk : storage_data_disk) {
				block += dataDisk.getBlock(level+1);
			}
		}
		if (storage_image_reference != null) {
			block += storage_image_reference.getBlock(level+1);
		}

		block += storage_os_disk.getBlock(level+1);

		if (tags != null) {
			block += tags.getBlock(level+1);
		}
		if (zones != null) {
			block += "zones = \"" + zones + "\"\n";
		}
		block += headIndent + "}\n";
		return block;
	}
}
