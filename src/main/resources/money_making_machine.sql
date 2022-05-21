-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: money_making_machine
-- ------------------------------------------------------
-- Server version	5.7.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `act_evt_log`
--

DROP TABLE IF EXISTS `act_evt_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_evt_log`
--

LOCK TABLES `act_evt_log` WRITE;
/*!40000 ALTER TABLE `act_evt_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_evt_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_bytearray`
--

DROP TABLE IF EXISTS `act_ge_bytearray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_bytearray`
--

LOCK TABLES `act_ge_bytearray` WRITE;
/*!40000 ALTER TABLE `act_ge_bytearray` DISABLE KEYS */;
INSERT INTO `act_ge_bytearray` VALUES ('10',1,'请假流程.process_5.png','8','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0�\0\0\0\�\0\0\0|��\0\0�IDATx^\�\�[�]u��-�Ƅ�\�\�\�g�$��`0j|��ml\�\�(\"A��\�ML|\0%�v�\�Q\��\�E)-DN�܊@z9\nګ�Ph��Yg�֙=\�=-ô3��f>\�7\�\�{�3���w�}\�t\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0`�UUu\�֭[���Tk׮�\��^�\�\�\�}Æ\r#\�֭�N�:�\�>C\0�(�\�w_�s\�\�\���տ��/�\�\�\�=~�\�ׯ�KĒ�ߨtf\�\�\'�0 \�(U\�`�o\�d\�cǎ(\r�\��ҙ�r�u�\0���Te$��i�oT:3TN�\�\0\"n�\�7^r�4����q�\0�-\r����\��\�U����I�;Nko\'Ǘ������3���Lix\�g�\�\�^\\=|\�\�G$N��\�\�\�ԓ�4����q�\0�)\r{|U_a\�\�\���o{�z2�3TV2\�\0b2�\��\��\�B7q^{{�z2�3TV2\�\0b2�\�{����n\���2�d,\rf��d�!\0�\�PV2�3TV2\�\0b2�!^�\�.\�\�y\�\�e\�\�X\Z\�PY\�8C\0�ɔ��7\�\�W���\�\�\�ԓ�4����q�\0�)\r{�}�z\�o��8-\�ko/SO\�\�`�\�J\�`@L�4D��ϊ�\�����\�K\�\�`�\�J\�`@L�4<X=�馾\��\�y}\�˔��4����q�j�3��\0P��+\r�2Om���0t\��\��������3\�\�/�\�\0Ju\�\�p�`���\�U�����(�\�Ķ�\\2�3TV2\�PGi���;Bu�8ru�\�X\Z\�PY\�8C\�`0LT\Z&s�\�h�˶�\'�O\�\�`�\�J\�\�(�\0�a�\�\�.�4\��\�䓱4����q�:�\'�`��4\�\�K\�\�`�\�J\�\�(�\0�Ai(+K�*+g��x���d,\rf��d���\�	0�����4����q�:�\'0\�UUu\�ƍ��X�bϒ%KF���\�\�.�\�͛W}\�K_�.�\�\���/[�졅�پ�l�4��������3ԙD��\�g?{F�>��;w\��\��:�c���\����]��\0\'M]8O_�z��/������\�Ճ>Xmٲ�ڻwo\�k|�\��\�w^u�\�������씆���4����q�:\�(�s\�̹�.��\�J\�d�/.\�\���z\��n_�lYu\�%�T�������h��\�.��\�gu\��T/hn\�;+���d,\rf��d��\�\�3\�ٺ@T��\�M\�5�D\�O�����\�\�~��\��\�\�\�n9)q����bA��}=)\re%ci0Ce%\�uZų.�W\r\r���\���\�W\�]w]�v\�\�	��\�\��خU>Gc=\�0}\�u\�;\�\�x�\�_��#�\�\�~\�a�za��}}\�(\re%ci0Ce%\�uz�g����\�xq<�\�s��\�G\�s\�=\�^�\'\�\��q�\�\Z�m]\'��G:\�\�B;w\�l�O\�%��`����������4����q�:c\�s\�H\�xY\\�hQ�m۶��;)q��|o�̾^�۴i\�\�Q:Oԑζ\�\����G\�>�쏴�;���d,\rf��d��\�X�\�}x�ꫯ��������\�\�c?��g^���\�k\�\�\�����\�dN�U�V��s\�9�\�ן�\�PV2�3TV2\�Pg�x�\�<\�\�\��i�\�վr�\�z�\�\��\�����h�b�\�\�\�#YߺCi(+K�*+g�\�S<㹙S}x�hb�\��̺^��יS��\�\�-�f\���\�=\��92P\Z\�J\�\�`�\�J\�\��\�xa\�t����L�^��\��_�\�&�>�\�+���7=Z\�\'f\r\r=W\��\�mJCY)�4���\�6Cc\�fS<㭐&�\��W*�\��VK%�\�@r�\�r\�\��ġ��t\�҃s\�ν����,\�\��\�~����T\Z\�P\�d���u�)�\�_}{i=�b�ݟ���\ZHnٲe#3�0{W�\���g\�?\�\�\��\�1~����X\Z\�P�d���u�)�k֬i/�\'T\�\�\�)f���\�+��[��לi�\�O���\�\�di/�G[����Rri0C9�m�\����\�\�\�^�c�=?G1\�5�\\�w\�?��\���3��\�\�/0:\��YN��B\�Nw\�W\Z\�J�\�0f\�\�\�;��x��Gy�:p\���i�\�c]���X�\"�>ߪs\�\�\�����	\�71C�\�ѷ�\�-M�\�u;�\�s�Ŭ\�@r��C��לiU_\��+�%���x��x\�D;�6m�֭[׼`�}�K/���\�\�wz���>R���\�_�z�a\��y��z�9׮]\������^�\�W\�#}ۗ��\�\'C\���4��\�^�c��\�ۺ\�\0���\�?\�\�9�\�޽;j/\�t{a\�&\�ä��\�g���\��\�\�\�g�ݜG~\�畞G<�\�\���\��\�f�\��ؾ�xΙ3�o�\�\�?���\�)%�x\��s��z\�\��#�x�}��_�\�\�\n�m޼�\�y=gh���\�I���ҰgϞ\�O�S_n�\�\�*�ݾ\�K/�Ԕ\�׿��\�SO=\�w~\�)�xf��n�\\�re�z�\�\�;/_\���ﻳ\���\�/?�`ƶ�\�g�}\�k�뮻.޳�\�\�駟R�m�\�\�\�\��=\�\�\�x\�\�\��ƿg�U\���؎y�J�X}�\�K\�/�˾�M����e\"�/n\��\�\'?\�w^\�)�4d��}��U���\�\'�x�)���\�/�ٸ�\�;�\�}�\�洝;w6\��\�l\��\�\�~\�\��9\������Ջ/�X�u\�Yյ\�^\�w%%\����\�\�ܫځ��.]�\�L��\�E�v���p\�Z\�{�\\\Z�\�\�۔��G�\���\��\�l׾\��\���\�\�o~s�ͪU��\�/5%��\�0C\�\\p�}wZz\�w�]�pasڧ>��\�\�_�z�\�SO=�zի^U�\�mo��y晾���l3\�}<��\��3���g�F\�l$\�\'a�\\\Z\�Sg��\�\�\�7\�|\�\�\�nx�����ǫw�\�]\�Q���KLI�a�J��n�X\�,��\�o^(T�\�͋\�\��~��\�\�3\�\�MozSs�;\��\�\�n�\�\�\�Ƨ�rJu�\�W������7�]G)\�6C3�\�E��\�\�Ƌg\��\Z(\�E]th�n�o��\�K�ٿ���[o���\�\�>V=��\�Ն\r��x�\��\�۫3\�<���\��\�>��>P�q\�\����W#\�{���]b���P�E&{\��{\��\�x�뮻�wU�\���׼��\�O~\�\r=��ӏ���q�:c�(�>�Hixx���/��:|�p{�9�\��\Z\Z���9s.m��^\Z\�a\�8\��\�w�{�\�\��\�\�\�o{��}�ڲe\�\�w&�\�\�uO��u��I\�GFKL\�\�P�E�Xv�\�۽\�}aZ\�Q�xΝ;��\��؜�j�;,1{�\�>o��[n\�\�I\�8C��\�y\�\�V۶mk/�\�%�w\�9\�Ϭ\�5P�E���H�N˗/�^\�v��;�\�KC�\�\��\��~s\�\��q�\�G?\�Ō\�ku\�\���k��O|�9�}4\���\�S>��\�\��]GI\�X\ZJ��\�d�x�\�|Ϙ�(-ݷ\���\�޷���q�:=\�3R�\�\�\��D��\\xᅽG;Ӯ\�@\�\���,X�`4.���Ԗ�������ugQri��\�u��\���7T_�\�W���\�͍S�E\�e�]֔\�xX���\�֮][���om�\�\�\�O\�\�g�m����\�+)KC\�3\�\�\r7\�P}\�k_k^�\�=r��_���>^@�w��g��-\�,\�\�\�Nk>�\�\�_�r3w�*��u���3\��O�� ������\�g\\����\�-���\�k �z��2\�%���H۶m��O\Z�3g\�\�\�̤�\�E2�jv�|\�;\�Y��\�\�y�7o���\�\�};\��\�\�G\���������^R2��\�g���C\�\�t�g<�#歾�:��E1C�m\�S9JK\�\�\�XO{Jbs\�s��\�\�ZG:����\��\Z\Z�y\�\'\�\�g\��xn\�O\�וM�\��8/(���y�)KC�\�&\�\��u\�NH��%\����q�:c\�3�\�joY�\�|\��&�j�\�.�\�}Ngd6�\�@\"qOw�����V�\Z�\��\�Dc\�\�L��+[i�\�\�X\Z\�PY\�8C��\�Ǝ|�?\���Z�O���\�\�\�\�x�\�_\��8=\�\�}ˤ��Ζ�\ZH�^|>\\\�\�3<<<r����N�}>\�}:o�\�m�\��z\��i\�;+���d,\rf��d��N�x�Xgc�m�\�W�Y�^I\�[i\�Ҿ���.]��\��5k>�䓇�\�\�3Z�\�\�]�vz��\�_�\�o~�.�h�ܹs\���\��JCY\�X\Z\�PY\�8C�	�gWw���T+�r���O����u��\n\�sxlъ��\�N_9�?\�Bi(+K�*+g�s�\�\�e�������4����q�:�(�\0\�JCY\�X\Z\�PY\�8C\�`0(\re%ci0Ce%\�uO���4��������3\�Q<�\�PV2�3TV2\�PG�JCY\�X\Z\�PY\�8C\�`0(\re%ci0Ce%\�uO���4��������3\�Q<�\�PV2�3TV2\�P\�?\��D�R)\re%ci0Ce%\�u�\�\���R)\re%ci0Ce%\�0 �����4����q�\0JCY\�X\Z\�PY\�8C\0���d,\rf��d�!\0�\�PV2�3TV2\�\0Bi(+K�*+g��4��������3��P\Z\�J\�\�`�\�J\�`@(\re%ci0Ce%\�0 �����4����q�\0JCY\�X\Z\�PY\�8C\0��k\�V\��O�w8��4��r�u�\06lٹsg\�\r�\�|�o\���ui\�\���\����3��X�n\�w֯_?�cǎQG�NN\�\��H]�ׅ!�V-i��Jg�N~�\�\0$n�\�(I<D\'\'-��_\��\�d?�\�������K\�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\��\0&�̓�\�\�\0\0\0\0IEND�B`�',1),('2',5,'source',NULL,'{\"resourceId\":\"1\",\"properties\":{\"process_id\":\"process_5\",\"name\":\"\",\"documentation\":\"\",\"process_author\":\"admin\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\"},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[{\"resourceId\":\"sid-8EFE6380-4EF9-468C-9140-4C9B6D597183\",\"properties\":{\"overrideid\":\"start\",\"name\":\"开始\",\"documentation\":\"\",\"executionlisteners\":\"\",\"initiator\":\"\",\"formkeydefinition\":\"\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\"}],\"bounds\":{\"lowerRight\":{\"x\":225,\"y\":195},\"upperLeft\":{\"x\":195,\"y\":165}},\"dockers\":[]},{\"resourceId\":\"sid-9445F432-E485-496C-BF25-A086CFC79391\",\"properties\":{\"overrideid\":\"input\",\"name\":\"录入\",\"documentation\":\"000\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\"}],\"bounds\":{\"lowerRight\":{\"x\":370,\"y\":220},\"upperLeft\":{\"x\":270,\"y\":140}},\"dockers\":[]},{\"resourceId\":\"sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-9445F432-E485-496C-BF25-A086CFC79391\"}],\"bounds\":{\"lowerRight\":{\"x\":269.15625,\"y\":180},\"upperLeft\":{\"x\":225.609375,\"y\":180}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-9445F432-E485-496C-BF25-A086CFC79391\"}},{\"resourceId\":\"sid-243B4E4F-36F9-45E3-AC70-155046E26D96\",\"properties\":{\"overrideid\":\"audit\",\"name\":\"审核\",\"documentation\":\"011\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\"},{\"resourceId\":\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\"}],\"bounds\":{\"lowerRight\":{\"x\":515,\"y\":220},\"upperLeft\":{\"x\":415,\"y\":140}},\"dockers\":[]},{\"resourceId\":\"sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-243B4E4F-36F9-45E3-AC70-155046E26D96\"}],\"bounds\":{\"lowerRight\":{\"x\":414.15625,\"y\":180},\"upperLeft\":{\"x\":370.84375,\"y\":180}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-243B4E4F-36F9-45E3-AC70-155046E26D96\"}},{\"resourceId\":\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\",\"properties\":{\"overrideid\":\"\",\"name\":\"结束\",\"documentation\":\"\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"EndNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":588,\"y\":194},\"upperLeft\":{\"x\":560,\"y\":166}},\"dockers\":[]},{\"resourceId\":\"sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\"}],\"bounds\":{\"lowerRight\":{\"x\":559.375,\"y\":180},\"upperLeft\":{\"x\":515.390625,\"y\":180}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":14,\"y\":14}],\"target\":{\"resourceId\":\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\"}},{\"resourceId\":\"sid-328F220C-A4F8-4EBE-9A7E-4243BCBB4A03\",\"properties\":{\"overrideid\":\"end\",\"name\":\"结束\",\"documentation\":\"\",\"text\":\"\"},\"stencil\":{\"id\":\"TextAnnotation\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":660,\"y\":205},\"upperLeft\":{\"x\":560,\"y\":155}},\"dockers\":[]},{\"resourceId\":\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\"},\"stencil\":{\"id\":\"Association\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-328F220C-A4F8-4EBE-9A7E-4243BCBB4A03\"}],\"bounds\":{\"lowerRight\":{\"x\":559.2500542490575,\"y\":179.47395889842767},\"upperLeft\":{\"x\":515.4999457509425,\"y\":179.01822860157233}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":1,\"y\":24}],\"target\":{\"resourceId\":\"sid-328F220C-A4F8-4EBE-9A7E-4243BCBB4A03\"}}],\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":950},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}',NULL),('3',1,'source-extra',NULL,'�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\�\0\0\0\0\0�;\��\0\0\0 cHRM\0\0z&\0\0��\0\0�\0\0\0�\�\0\0u0\0\0\�`\0\0:�\0\0p��Q<\0\0\0gAMA\0\0��|�Q�\0\0\0sRGB\0�\�\�\0\0\0bKGD\0�\0�\0�����\0\0\0	pHYs\0\0\�\0\0\��+\0\0\Z&IDATx\�\�\��Ue�0�53�p�Pᓢ\�Q�Y���\���h^-hVv\�\�W=\�\�\�OYdV\��x\�i\�(���F��\�\rAP�F�\�e����\�M\�aFa�����=\�\�^{��\�0\��ϻ��]\�zW�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\�\�U��%\�r�����\�y+W��\�\�\�\��]��:m\�Vj߾��v\�\�-\�С\�C�n8x�\�\'\�b$\�@5o޼�\�\�\��$}\�v\�ӧO֣G��cǎY� V9[)%�ٚ5k�%K�d,X_UU�xZ��!C�\�C�!�mD.���;w\��9s\�ү_�l�\�w\�*++UL+���\�\�ϟ��:_�\����\�\�+�(������-�TN\�g�}��}�JhZ��N���\��vN\��L�r��\�cZR�*\0�#N}Ϟ=��FBӭ[�M��^>/[0\���\�?f�\�<�\�.}%\�ХW־��\�\�J\�\�\�Y�\�\�;\�\�\�|v��\�\�|�\�K\�\�C\�\�_U��\�\"�\Z��Kh֬X���\�\r٪%���\r\��%�c]lc\�E���ߐދQb�r�!�mB\�P[[\�%ƃ6fɜ��D��X\�h)�\�YYYy�I��C�[�\�hV�\\9�w\�\�M�]�\�kM������\rx��]w\����x��\�b$\�@�P[[; �\�jJ]ݺfmc˥\�kEEőb�r�!h\r\�T��֮]۱S�N*�\r\�ܹs��b[.�\�H��ºu\�*ڷo�{T4s[�C�]\�C\'1D�\��C)�-�\�岊�����vi\�6�\\\�}�C�[�\�xO\�\��Yeզ��j\�)\�\��P�$\�@yة�>Y�]?�\��\�=d]{��\nBc�L\�rن��6Y�_���\�\"\�-j\�\�\�?��}�j\�\�M��Z:;{��\�>{��u\�\�Se!�h�aÆ�\�7�KǍw�ZEb��\\.[�ڣ\�\�Y��c\�\�\�&%;s�vM\�k��\�.�ŕ?\�1D��d֛�\�h[ީ��1��ԼtO�\�z�C@�a�1�բ�ns�RŞ?C�\�\�!�\�i\�\�|-bH-\�P\n`��\���\��\�\0\0$\�\0\0 1\0\0�1\0\0�r�l�\\.W�\�c��0s\�\�s_y\��W�Z\�e��\�\�K�.�\�y\�\�w\�\�uuUU\�\��\�ٳg�|̘1��5�\�\�\�SO\�#�\�ǥ��?***��U��\�7�y�,H\���\�߳aÆ�&N���\ZCb��w�\��~q�\��X�`A���\�?;�裳^�ze)!\�z�\�-Y��ݲe\�vZ�p\�NӧO�`Μ9\�9r\�5k�z\�u\�ݧ���C�^YY�x>$�Wlzw\�H��\���\�\�Æ\r{���\�	&<�%\�@{챯_~�\�/^\��\�N\���w��\"9��| ;���\��VN�6\��cǎ��k_�\�3\����?~�\Zh����ݸ\�\�1\�x�!�m�sJ�\'\�r�oh�%\�@�\�0V\�����3\�\�}\�s\�QG�UUUm�\�#y�ԧ>�p�\�\�q>>a�O?��S�<Y\����C��\�\�\�R\�ݽ��}��\�\�{\�\�\�\�߿\��lٲeٜ9s�iӦe\�=�\\�n\�\�\���뿧\�\rM\���Ԭ\�$\�))�\�;�<y���\�??�\�G>\�\�cE2}\�\�FOr�Q�F�\�3\�y뭷ޠ�Zưa\�\�N\�쵩\�*���vXv\�)�\�\�J\��\r<8�$\�v\�mك>�mذ!v\�%%\���\�~eܸq7�\��aV\nhD��2�\�K/ݪ��T\�+�\�T]]}u�l�e��\�iiR�뮻f�_~yv\�9\�4�7&���\�u���\�<���Zbe\��\�\�\�\�3��\�of�{�n\�c\���\�t\�ر\�\�SO=��j���m׮\�\�bR<`��\�\��h�x]�>�SL�+++\�k�%\�P�b��?��ϣ�?��\�)n(�\�\�O��oרq�櫪��E]]]�[8zz��\�ogݺu۪c\�\�\�8%=\�=\�\�m�1��I�&�|ѢE\�B�\��\�\�~v\�Ν;\"�R\�\0\�vl!A\�.�\���N�K�\�8^\�\�\�j�%\�PV\�\��<�\�Y1%ۖ\�>\�q�#F�����\�\��~�\��2Ȼ�zw�W�\�֯_���,\\��tV��\�z\�-��\rb(.�k\����\��\��\�\�^�m��\n}�\�\�\�\�Դ�i}��A�u=z��qg�m}ǥ�%`T<6����\�\�\�mcǎ}tGxg͚\�ě\�\r��\�>��\�o�~ƌ\�}�ݗ}\�[\��\�P\�1\�\�ȑ#���\�\�׿��&�9�\�s��\�������\�l�\�\�\�%�\\\"`\�9���5-�\�\��v��S�\�\��!�;u\�\�◞��G{�\����3g~->�\Z�yGk���\�x\�s\�=w|z:z;�\�\�KN�	~��HR~��\�5�\�\�1�&[�|y��P�1C�\�\��y�Ǘ�;\��m�E�4<���\\g\�]w]~yѢE\�\�����z\�\�!�\"b6\�+\��y\��\�M�S�y|qy�}�\�\�\�\'�T7��\�SO\�ӊ\�\�\�-�\�\�\�W_�\�Gl\�Yy:蠊\��\�6\�\�\�	\��\���,���L��t\�\�\�\�wܑ?u\Zw.���)\�J�\�_��ϋg >�\�Oo\\7\�\��\�G\�.��\��\r\"���/e\'�tR�\�{\�`�\�7�\�\�pP��\�Z\�[9&\�i��\�\�O~���\�\�\�ϕK�aǷbŊ�\�{\���\�\�\�ׯ_di�$8-+�\�\�\�\�wJN\"i\�}�ݳ�\�;/� w\�\�\�F\�P�\�\��{\�7�X:\���C֮]�\�c�Xv��\�\�{�\�f@�\�:b,�ch\��\�ón�!�0\�\�.m�w^�`Ai(��xhܸq������C[a\�\'\�\��\��|\���wz�\�)n\�њ\Z��?m�1��\�˗Wo\���x\�|@��| ��gez|*}�h��\�7\�x#��\�k�\�>o޼\��\�_\�O\�~�\�|�Ԥ���\�#�<�On\�8� �u+W�̞~�\�L����r3bĈF�\�x\�\�����\�\�p�t\�\���ѣGg\'�xbv�\�W\�\�C�b�\'�|2;�\�\�tzD/-�z0�O��\�rX����-QL��RR���\�\�\�v�\�\�\�\�Rbe!}V\�\�N\��g�\��\�R�\�\�uM\�\�iӦŔsm\�?=�|p�\�/f{\�W~\�ĉ��g�uV��\�|&�\�G?���:n\�P���\��\�\'�}�������D��\�\�\�P\�\�R�\�C�����\�p�\�a�1\��\�v\�M7\�\�]x\��\�\�\���\�\�4�\�?<\�СC�����\�gM$�\r�ƶ\�\�8���\�\�\�\�\�\�Rbe�s\�\�\�W�X\�n[�\�LI\���c\�FC{\�6�u7\'�*\�3U]�\�\�T�8p\��m5�)\�z;\�o������\�O<�]y\�ُ~��\�{\�\��N�0a�\�8�Y\�\�~��_\��\�N\��,�\Z5�M\�\�A���\�K�_*�ZN�\0�S̞=;�X|g&�V�Z�O,�w�\�3\�\�\'ϑH����\�A���\"1�^\�!C���-���R�J�1ԩ�.\�>�f�\�\�\�\�b��c(]�v]�lٲ��eb�z�\�%\�Ў7\�m��6�\�������\��$3����e%\�C��\�/\�\�\���\�n�\�{\�\��\�\�_D\�@ズHLz\�Fo�G/s1y�袋�\�u�4\�U\�m8n��\�/�P��f�n\�\�4�X|�\�\�n\�/\�:4?CE�9��O|\��\�&O��=�\�c\�e�]�\�}�\��mb\�\�c(�P�xY�¨KI��J��{Z\�_u�\�\�VM�\��%\����CY����gMM\�N1����\�/\�/�l7��Lzة�D\�bÆ\r���b&�x��ᣏ>:\�<f̘�)ㆉq� \�T�\\�\�\�+�ꪫ�ME2ݧO$eC!\�\�\��\�D�\�S���c\�~�{�\�\'\�!\�Z\��⸝p�9�c\��8�;*M��\�c��\�\�k\�\�8z�[�\�.�1(��H��,\�?>�\�\�4L\�ʔ)Sڥ�{�ӯ�\'+w�D&�\��\�[��\�\�����b����\�g�mq�d\'�|r�8�V�\�\�7?g�-�ܒ\�ы\�\���1hРl���ٶ��\�{\�C!�\�\�\\\�\�\�ي\�ib\�p\�m>1��T\�\\S���\�?\��\Z\��������Է\�~{�w�Mch3�\�Ҿ�\�~\'\�r��o\�a*�1���LbeaΜ9?\�\�ί���\�7�H?�fѢE�\�/m�_�rGIdJ�\�\�O]w\�\�1?g�G�?\r\Zc�C<���\�I���_~9\�\�_ \�T�p���:z�\��c(Ĵ��S76<\��sSb�z\�|c��\�w\���J�\�_�2?{H�\�e\�c(������u,\�]+\�\�5.��\�>�\�q6$\�\�\�\���I��,�3浑#G�����ۢ\�x\�ĉJ{n�ۋ��\���\�x��H�CϞ=�c<�\�\�|�M�bLq���\�m�|b��/�\�\�O���n����\�b(\�\�8�\�\�\��r\�9\���1��~���\�\�G\�Zbee͚5\�F�\�8�]\�[���\r6̻�\�;�������=\�\��S\�6b�%�bj�����-���3gN\�(R\�-�^ꋽk;�JU\0�r\�u\�ݗ\Z�g\���ּb>w\�UWEo�\�	&<�\��-�Fu��\�q+\�\�5jT�8�+�߫��CY���:q\�\�ڸ��5L�:��O�~Lj\��Km4_��i/�E�\�/z\�\�\�8^\�W\�/^\\̑�h�%\�P�RR��5kNJ\r\�\�҉�[\�ܹs\�=z��uuug\�\�Q\�\0[\�^�����0\�\�Y��\�}\�{�a\���袋\�fϞ]\�)^\�\�^K����;vrJ�G~��\�]\�R=\�S�N�9o����	&�I-l�B{��br=\�1]c\�M\�\�\�M��b��T��\�񾢽./.��&\�z\�7:t\�W\\1�SN�yHwn\�yq�]�)�>}��uuu_\�\���q\�\�ݘ\�\�W+++\'���Ę\�)S�\�oڳ�\�{\�\� ��bJ��\�ϒ%K�\�p�\�t�3f\�\�f�(�/M\��P\�\�h\�q\�i�}r���뮻�1bD�A�uߜy�S��FL\�v\�w\�]!�\�r��\��f�U�Vk�����&==&\�\�Tn1t�wQQ�<)�\�\�\�^K��F��8\Zǣ�z�5\�\\s\�\�ѣ��Ї>�\�!�Rѷo\�n={�\�Jna�%3g\�|}ʔ)\��\�u�bJ6W3l���\�h�+++/Kˇl�\�N\��%\�k�1�y�\�X>pꩧ\��\�/���\��GEEE�BOC\�K8�\�\�r���\�6l8\�d�\0۷����:.�\�\��z�B{=/�i�?\��{R{}��\Z�14C���M�\0��faV\n\0`Gѹ�u7\�uUchY1e\�n�4ҽU\r@��am\�\�J��������oP\�6���*-?�R�;7�o\�~?K%n��T.J嬒m#�\�T\'@���(�\�\�\��Hexa}�T�\��8�\�R\�\�}\ZYs#��Z%\�@�\\*��\��RYQx=�YX~���\�B�[�\�g�2(�/�2.�Z\�\n\�*�LtL��\��rG*���P�\\t|*=K�O\�\�ީ��^�Z%\��\�=Xx|3�G\n�eXU�-|)���J\�C��\��\�T~RH�\�uW�в.\�ꇲ\�Ev]��3y��u.Y~<���6::.f\�G�|Ia9:9nO\�*\�*1�纬�4^�_�\�>���\�5���:��R�1�\��E�O*���\�B\�\��m7�,�XH��B{|ca9\�\�{���c\'U*1\�np�G�؃Inq\�D\�x�\"q*ݲ����g�C/�\�T�f�=ƿLeo�1@�[THr\�N\�謾g8z�ۗ\�\����\�gg��Hi�^\�1Fb��qi7��H\�ڒmCJ���\�^Y�Ev�f�c�#�Q\�KQl�\�U�\0-cذa���z���m����gƌk�y晏e�\�\�\�\�%jWb�\��B�+�\�*\�K�5c\�T޴�~XE�dVJ�\����e�7���\�1\r\�g�:�/�\�EϤ�o\����\�@��\�\�R� �\�S�f*s԰\�h\\4��l\�~���\�TN\�\�{�\�5h\\שJ�V�\�g�g\�\�\�\��\�8\�\�%�t*<\�UH�s�\�\��9�aY�u\"�\�wSyB\�J���J�\�\��wٯcV?>-\�\�N\���ҽA���\�h1�f�/.�bmA�}^/<\�0��\�xj#ۢ���\�b\��B\�*1��V^\�6����(\�\�<��+Y�\��\�W�\0�\��F\�\�\�y񺐚B)5���U�\�x�9��\�Ķ��\�}�\�sL�2\0\�U�`KUTTd�\\NE�\�}�\�lC�[�\�hڵk�~\�:\��o��V\�m�FQn1c��$5\�V�^�\"ڀ�+W\�\�B��!\�-�@b�	:txh\��߷�\�ͫ\�\�r�\�\�C 1ڄ�+W����f}]]�\�؎R�\�^�|�\'\�\�\�b�r�!�m\�\�������||�|3\�mO3f\��Kz��1\���\�b$\�@��v\�\�/̝;�v��\�*c;x\�7�w\�ҥG�\��\�b�r�!hiU�\0h��o�y\��\�ßZ�p\�	ݻw\�P]]�R�aB�\�/N	\�\�C��&�(\��1\�f\�t\�M3\�<�\�\�\�C��\\.Wۭ[�\�1?-�#ƃ>�\�w͛7\�\�T\�gq\��\�\�CВ�>�V�2eʀ�ԌJ���\�\�kA�>}�w\�رGuuu��UV�Y�famm\�K)�Y�\�oL\�����2K!�@b�Q�&M\�/}\������R��Gz\�V�N�\�y�_O��\�\�;�ERbH\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\���\�5�\08\�A\0\0\0\0IEND�B`�',NULL),('9',1,'请假流程.bpmn20.xml','8','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n  <process id=\"process_5\" isExecutable=\"true\">\n    <startEvent id=\"start\" name=\"开始\"></startEvent>\n    <userTask id=\"input\" name=\"录入\" activiti:assignee=\"admin\">\n      <documentation>000</documentation>\n      <extensionElements>\n        <modeler:initiator-can-complete xmlns:modeler=\"http://activiti.com/modeler\"><![CDATA[false]]></modeler:initiator-can-complete>\n      </extensionElements>\n    </userTask>\n    <sequenceFlow id=\"sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\" sourceRef=\"start\" targetRef=\"input\"></sequenceFlow>\n    <userTask id=\"audit\" name=\"审核\" activiti:assignee=\"admin\">\n      <documentation>011</documentation>\n      <extensionElements>\n        <modeler:initiator-can-complete xmlns:modeler=\"http://activiti.com/modeler\"><![CDATA[false]]></modeler:initiator-can-complete>\n      </extensionElements>\n    </userTask>\n    <sequenceFlow id=\"sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\" sourceRef=\"input\" targetRef=\"audit\"></sequenceFlow>\n    <endEvent id=\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\" name=\"结束\"></endEvent>\n    <sequenceFlow id=\"sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\" sourceRef=\"audit\" targetRef=\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\"></sequenceFlow>\n    <textAnnotation id=\"end\"></textAnnotation>\n    <association id=\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\" sourceRef=\"audit\" targetRef=\"end\" associationDirection=\"None\"></association>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process_5\">\n    <bpmndi:BPMNPlane bpmnElement=\"process_5\" id=\"BPMNPlane_process_5\">\n      <bpmndi:BPMNShape bpmnElement=\"start\" id=\"BPMNShape_start\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"195.0\" y=\"165.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"input\" id=\"BPMNShape_input\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"270.0\" y=\"140.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"audit\" id=\"BPMNShape_audit\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"415.0\" y=\"140.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\" id=\"BPMNShape_sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"560.0\" y=\"166.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"end\" id=\"BPMNShape_end\">\n        <omgdc:Bounds height=\"50.0\" width=\"100.0\" x=\"560.0\" y=\"155.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\" id=\"BPMNShape_sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\">\n        <omgdc:Bounds height=\"0.45573029685533584\" width=\"43.75010849811497\" x=\"515.4999457509425\" y=\"179.01822860157233\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\" id=\"BPMNEdge_sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\">\n        <omgdi:waypoint x=\"370.0\" y=\"180.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"415.0\" y=\"180.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\" id=\"BPMNEdge_sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\">\n        <omgdi:waypoint x=\"515.0\" y=\"179.47916666666666\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"560.0\" y=\"179.01041666666666\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\" id=\"BPMNEdge_sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\">\n        <omgdi:waypoint x=\"225.0\" y=\"180.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"270.0\" y=\"180.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\" id=\"BPMNEdge_sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\">\n        <omgdi:waypoint x=\"515.0\" y=\"180.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"560.0\" y=\"180.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0);
/*!40000 ALTER TABLE `act_ge_bytearray` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_property`
--

DROP TABLE IF EXISTS `act_ge_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_property`
--

LOCK TABLES `act_ge_property` WRITE;
/*!40000 ALTER TABLE `act_ge_property` DISABLE KEYS */;
INSERT INTO `act_ge_property` VALUES ('cfg.execution-related-entities-count','false',1),('next.dbid','32501',14),('schema.history','create(6.0.0.4)',1),('schema.version','6.0.0.4',1);
/*!40000 ALTER TABLE `act_ge_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_actinst`
--

DROP TABLE IF EXISTS `act_hi_actinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_actinst`
--

LOCK TABLES `act_hi_actinst` WRITE;
/*!40000 ALTER TABLE `act_hi_actinst` DISABLE KEYS */;
INSERT INTO `act_hi_actinst` VALUES ('15006','process_5:1:11','15001','15005','start',NULL,NULL,'开始','startEvent',NULL,'2022-05-08 05:38:45.454','2022-05-08 05:38:45.454',0,NULL,''),('15007','process_5:1:11','15001','15005','input','15008',NULL,'录入','userTask','admin','2022-05-08 05:38:45.454','2022-05-08 06:57:30.336',4724882,NULL,''),('17506','process_5:1:11','17501','17505','start',NULL,NULL,'开始','startEvent',NULL,'2022-05-08 06:00:58.924','2022-05-08 06:00:58.926',2,NULL,''),('17507','process_5:1:11','17501','17505','input','17508',NULL,'录入','userTask','admin','2022-05-08 06:00:58.929','2022-05-08 06:57:39.436',3400507,NULL,''),('27501','process_5:1:11','15001','15005','audit','27502',NULL,'审核','userTask','admin','2022-05-08 06:57:30.397',NULL,NULL,NULL,''),('27503','process_5:1:11','17501','17505','audit','27504',NULL,'审核','userTask','admin','2022-05-08 06:57:39.437',NULL,NULL,NULL,''),('27510','process_5:1:11','27505','27509','start',NULL,NULL,'开始','startEvent',NULL,'2022-05-08 06:59:54.767','2022-05-08 06:59:54.767',0,NULL,''),('27511','process_5:1:11','27505','27509','input','27512',NULL,'录入','userTask','admin','2022-05-08 06:59:54.768','2022-05-08 07:00:01.247',6479,NULL,''),('27514','process_5:1:11','27505','27509','audit','27515',NULL,'审核','userTask','admin','2022-05-08 07:00:01.247',NULL,NULL,NULL,''),('30006','process_5:1:11','30001','30005','start',NULL,NULL,'开始','startEvent',NULL,'2022-05-08 07:03:55.581','2022-05-08 07:03:55.583',2,NULL,''),('30007','process_5:1:11','30001','30005','input','30008',NULL,'录入','userTask','admin','2022-05-08 07:03:55.584','2022-05-08 07:04:03.479',7895,NULL,''),('30010','process_5:1:11','30001','30005','audit','30011',NULL,'审核','userTask','admin','2022-05-08 07:04:03.479',NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `act_hi_actinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_attachment`
--

DROP TABLE IF EXISTS `act_hi_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_attachment`
--

LOCK TABLES `act_hi_attachment` WRITE;
/*!40000 ALTER TABLE `act_hi_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_comment`
--

DROP TABLE IF EXISTS `act_hi_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_comment`
--

LOCK TABLES `act_hi_comment` WRITE;
/*!40000 ALTER TABLE `act_hi_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_detail`
--

DROP TABLE IF EXISTS `act_hi_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_detail`
--

LOCK TABLES `act_hi_detail` WRITE;
/*!40000 ALTER TABLE `act_hi_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_identitylink`
--

DROP TABLE IF EXISTS `act_hi_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_identitylink`
--

LOCK TABLES `act_hi_identitylink` WRITE;
/*!40000 ALTER TABLE `act_hi_identitylink` DISABLE KEYS */;
INSERT INTO `act_hi_identitylink` VALUES ('15009',NULL,'participant','admin',NULL,'15001'),('17509',NULL,'participant','admin',NULL,'17501'),('27513',NULL,'participant','admin',NULL,'27505'),('30009',NULL,'participant','admin',NULL,'30001');
/*!40000 ALTER TABLE `act_hi_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_procinst`
--

DROP TABLE IF EXISTS `act_hi_procinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_procinst`
--

LOCK TABLES `act_hi_procinst` WRITE;
/*!40000 ALTER TABLE `act_hi_procinst` DISABLE KEYS */;
INSERT INTO `act_hi_procinst` VALUES ('15001','15001','6','process_5:1:11','2022-05-08 05:38:45.406',NULL,NULL,NULL,'start',NULL,NULL,NULL,'',NULL),('17501','17501','7','process_5:1:11','2022-05-08 06:00:58.814',NULL,NULL,NULL,'start',NULL,NULL,NULL,'',NULL),('27505','27505','8','process_5:1:11','2022-05-08 06:59:54.765',NULL,NULL,NULL,'start',NULL,NULL,NULL,'',NULL),('30001','30001','9','process_5:1:11','2022-05-08 07:03:55.521',NULL,NULL,NULL,'start',NULL,NULL,NULL,'',NULL);
/*!40000 ALTER TABLE `act_hi_procinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_taskinst`
--

DROP TABLE IF EXISTS `act_hi_taskinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_taskinst`
--

LOCK TABLES `act_hi_taskinst` WRITE;
/*!40000 ALTER TABLE `act_hi_taskinst` DISABLE KEYS */;
INSERT INTO `act_hi_taskinst` VALUES ('15008','process_5:1:11','input','15001','15005','录入',NULL,'000',NULL,'admin','2022-05-08 05:38:45.455',NULL,'2022-05-08 06:57:30.331',4724876,NULL,50,NULL,NULL,NULL,''),('17508','process_5:1:11','input','17501','17505','录入',NULL,'000',NULL,'admin','2022-05-08 06:00:58.943',NULL,'2022-05-08 06:57:39.432',3400489,NULL,50,NULL,NULL,NULL,''),('27502','process_5:1:11','audit','15001','15005','审核',NULL,'011',NULL,'admin','2022-05-08 06:57:30.398',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('27504','process_5:1:11','audit','17501','17505','审核',NULL,'011',NULL,'admin','2022-05-08 06:57:39.437',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('27512','process_5:1:11','input','27505','27509','录入',NULL,'000',NULL,'admin','2022-05-08 06:59:54.768',NULL,'2022-05-08 07:00:01.241',6473,NULL,50,NULL,NULL,NULL,''),('27515','process_5:1:11','audit','27505','27509','审核',NULL,'011',NULL,'admin','2022-05-08 07:00:01.247',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('30008','process_5:1:11','input','30001','30005','录入',NULL,'000',NULL,'admin','2022-05-08 07:03:55.598',NULL,'2022-05-08 07:04:03.472',7874,NULL,50,NULL,NULL,NULL,''),('30011','process_5:1:11','audit','30001','30005','审核',NULL,'011',NULL,'admin','2022-05-08 07:04:03.480',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `act_hi_taskinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_varinst`
--

DROP TABLE IF EXISTS `act_hi_varinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_varinst`
--

LOCK TABLES `act_hi_varinst` WRITE;
/*!40000 ALTER TABLE `act_hi_varinst` DISABLE KEYS */;
INSERT INTO `act_hi_varinst` VALUES ('15002','15001','15001',NULL,'processDefinitionId','string',0,NULL,NULL,NULL,'process_5:1:11',NULL,'2022-05-08 05:38:45.453','2022-05-08 05:38:45.453'),('15003','15001','15001',NULL,'businessKey','long',0,NULL,NULL,6,'6',NULL,'2022-05-08 05:38:45.454','2022-05-08 05:38:45.454'),('15004','15001','15001',NULL,'userid','string',0,NULL,NULL,NULL,'admin',NULL,'2022-05-08 05:38:45.454','2022-05-08 05:38:45.454'),('17502','17501','17501',NULL,'processDefinitionId','string',0,NULL,NULL,NULL,'process_5:1:11',NULL,'2022-05-08 06:00:58.920','2022-05-08 06:00:58.920'),('17503','17501','17501',NULL,'businessKey','long',0,NULL,NULL,7,'7',NULL,'2022-05-08 06:00:58.921','2022-05-08 06:00:58.921'),('17504','17501','17501',NULL,'userid','string',0,NULL,NULL,NULL,'admin',NULL,'2022-05-08 06:00:58.922','2022-05-08 06:00:58.922'),('27506','27505','27505',NULL,'processDefinitionId','string',0,NULL,NULL,NULL,'process_5:1:11',NULL,'2022-05-08 06:59:54.766','2022-05-08 06:59:54.766'),('27507','27505','27505',NULL,'businessKey','long',0,NULL,NULL,8,'8',NULL,'2022-05-08 06:59:54.767','2022-05-08 06:59:54.767'),('27508','27505','27505',NULL,'userid','string',0,NULL,NULL,NULL,'admin',NULL,'2022-05-08 06:59:54.767','2022-05-08 06:59:54.767'),('30002','30001','30001',NULL,'processDefinitionId','string',0,NULL,NULL,NULL,'process_5:1:11',NULL,'2022-05-08 07:03:55.577','2022-05-08 07:03:55.577'),('30003','30001','30001',NULL,'businessKey','long',0,NULL,NULL,9,'9',NULL,'2022-05-08 07:03:55.578','2022-05-08 07:03:55.578'),('30004','30001','30001',NULL,'userid','string',0,NULL,NULL,NULL,'admin',NULL,'2022-05-08 07:03:55.578','2022-05-08 07:03:55.578');
/*!40000 ALTER TABLE `act_hi_varinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_group`
--

DROP TABLE IF EXISTS `act_id_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_group`
--

LOCK TABLES `act_id_group` WRITE;
/*!40000 ALTER TABLE `act_id_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_info`
--

DROP TABLE IF EXISTS `act_id_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_info`
--

LOCK TABLES `act_id_info` WRITE;
/*!40000 ALTER TABLE `act_id_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_membership`
--

DROP TABLE IF EXISTS `act_id_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_membership`
--

LOCK TABLES `act_id_membership` WRITE;
/*!40000 ALTER TABLE `act_id_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_user`
--

DROP TABLE IF EXISTS `act_id_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_user`
--

LOCK TABLES `act_id_user` WRITE;
/*!40000 ALTER TABLE `act_id_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_procdef_info`
--

DROP TABLE IF EXISTS `act_procdef_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_procdef_info`
--

LOCK TABLES `act_procdef_info` WRITE;
/*!40000 ALTER TABLE `act_procdef_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_procdef_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_deployment`
--

DROP TABLE IF EXISTS `act_re_deployment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_deployment`
--

LOCK TABLES `act_re_deployment` WRITE;
/*!40000 ALTER TABLE `act_re_deployment` DISABLE KEYS */;
INSERT INTO `act_re_deployment` VALUES ('8','请假流程',NULL,NULL,'','2022-05-07 20:58:10.671',NULL);
/*!40000 ALTER TABLE `act_re_deployment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_model`
--

DROP TABLE IF EXISTS `act_re_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_model`
--

LOCK TABLES `act_re_model` WRITE;
/*!40000 ALTER TABLE `act_re_model` DISABLE KEYS */;
INSERT INTO `act_re_model` VALUES ('1',8,'请假流程','请假流程_1',NULL,'2022-05-07 20:41:10.047','2022-05-07 20:58:11.083',1,'{\"name\":\"请假流程\",\"revision\":1,\"description\":\"这是一个请假流程\"}','8','2','3','');
/*!40000 ALTER TABLE `act_re_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_procdef`
--

DROP TABLE IF EXISTS `act_re_procdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_procdef`
--

LOCK TABLES `act_re_procdef` WRITE;
/*!40000 ALTER TABLE `act_re_procdef` DISABLE KEYS */;
INSERT INTO `act_re_procdef` VALUES ('process_5:1:11',1,'http://www.activiti.org/processdef',NULL,'process_5',1,'8','请假流程.bpmn20.xml','请假流程.process_5.png',NULL,0,1,1,'',NULL);
/*!40000 ALTER TABLE `act_re_procdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_deadletter_job`
--

DROP TABLE IF EXISTS `act_ru_deadletter_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_deadletter_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_deadletter_job`
--

LOCK TABLES `act_ru_deadletter_job` WRITE;
/*!40000 ALTER TABLE `act_ru_deadletter_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_deadletter_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_event_subscr`
--

DROP TABLE IF EXISTS `act_ru_event_subscr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_event_subscr`
--

LOCK TABLES `act_ru_event_subscr` WRITE;
/*!40000 ALTER TABLE `act_ru_event_subscr` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_event_subscr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_execution`
--

DROP TABLE IF EXISTS `act_ru_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_MI_ROOT_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `EVT_SUBSCR_COUNT_` int(11) DEFAULT NULL,
  `TASK_COUNT_` int(11) DEFAULT NULL,
  `JOB_COUNT_` int(11) DEFAULT NULL,
  `TIMER_JOB_COUNT_` int(11) DEFAULT NULL,
  `SUSP_JOB_COUNT_` int(11) DEFAULT NULL,
  `DEADLETTER_JOB_COUNT_` int(11) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_execution`
--

LOCK TABLES `act_ru_execution` WRITE;
/*!40000 ALTER TABLE `act_ru_execution` DISABLE KEYS */;
INSERT INTO `act_ru_execution` VALUES ('15001',1,'15001','6',NULL,'process_5:1:11',NULL,'15001',NULL,1,0,1,0,0,1,NULL,'',NULL,'2022-05-08 05:38:45.406',NULL,NULL,0,0,0,0,0,0,0,0,0),('15005',2,'15001',NULL,'15001','process_5:1:11',NULL,'15001','audit',1,0,0,0,0,1,NULL,'',NULL,'2022-05-08 05:38:45.454',NULL,NULL,0,0,0,0,0,0,0,0,0),('17501',1,'17501','7',NULL,'process_5:1:11',NULL,'17501',NULL,1,0,1,0,0,1,NULL,'',NULL,'2022-05-08 06:00:58.814',NULL,NULL,0,0,0,0,0,0,0,0,0),('17505',2,'17501',NULL,'17501','process_5:1:11',NULL,'17501','audit',1,0,0,0,0,1,NULL,'',NULL,'2022-05-08 06:00:58.922',NULL,NULL,0,0,0,0,0,0,0,0,0),('27505',1,'27505','8',NULL,'process_5:1:11',NULL,'27505',NULL,1,0,1,0,0,1,NULL,'',NULL,'2022-05-08 06:59:54.765',NULL,NULL,0,0,0,0,0,0,0,0,0),('27509',2,'27505',NULL,'27505','process_5:1:11',NULL,'27505','audit',1,0,0,0,0,1,NULL,'',NULL,'2022-05-08 06:59:54.767',NULL,NULL,0,0,0,0,0,0,0,0,0),('30001',1,'30001','9',NULL,'process_5:1:11',NULL,'30001',NULL,1,0,1,0,0,1,NULL,'',NULL,'2022-05-08 07:03:55.521',NULL,NULL,0,0,0,0,0,0,0,0,0),('30005',2,'30001',NULL,'30001','process_5:1:11',NULL,'30001','audit',1,0,0,0,0,1,NULL,'',NULL,'2022-05-08 07:03:55.578',NULL,NULL,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `act_ru_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_identitylink`
--

DROP TABLE IF EXISTS `act_ru_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_identitylink`
--

LOCK TABLES `act_ru_identitylink` WRITE;
/*!40000 ALTER TABLE `act_ru_identitylink` DISABLE KEYS */;
INSERT INTO `act_ru_identitylink` VALUES ('15009',1,NULL,'participant','admin',NULL,'15001',NULL),('17509',1,NULL,'participant','admin',NULL,'17501',NULL),('27513',1,NULL,'participant','admin',NULL,'27505',NULL),('30009',1,NULL,'participant','admin',NULL,'30001',NULL);
/*!40000 ALTER TABLE `act_ru_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_job`
--

DROP TABLE IF EXISTS `act_ru_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_job`
--

LOCK TABLES `act_ru_job` WRITE;
/*!40000 ALTER TABLE `act_ru_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_suspended_job`
--

DROP TABLE IF EXISTS `act_ru_suspended_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_suspended_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_suspended_job`
--

LOCK TABLES `act_ru_suspended_job` WRITE;
/*!40000 ALTER TABLE `act_ru_suspended_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_suspended_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_task`
--

DROP TABLE IF EXISTS `act_ru_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_task`
--

LOCK TABLES `act_ru_task` WRITE;
/*!40000 ALTER TABLE `act_ru_task` DISABLE KEYS */;
INSERT INTO `act_ru_task` VALUES ('27502',1,'15005','15001','process_5:1:11','审核',NULL,'011','audit',NULL,'admin',NULL,50,'2022-05-07 22:57:30.397',NULL,NULL,1,'',NULL,NULL),('27504',1,'17505','17501','process_5:1:11','审核',NULL,'011','audit',NULL,'admin',NULL,50,'2022-05-07 22:57:39.437',NULL,NULL,1,'',NULL,NULL),('27515',1,'27509','27505','process_5:1:11','审核',NULL,'011','audit',NULL,'admin',NULL,50,'2022-05-07 23:00:01.247',NULL,NULL,1,'',NULL,NULL),('30011',1,'30005','30001','process_5:1:11','审核',NULL,'011','audit',NULL,'admin',NULL,50,'2022-05-07 23:04:03.479',NULL,NULL,1,'',NULL,NULL);
/*!40000 ALTER TABLE `act_ru_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_timer_job`
--

DROP TABLE IF EXISTS `act_ru_timer_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_timer_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_TIMER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TIMER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_timer_job`
--

LOCK TABLES `act_ru_timer_job` WRITE;
/*!40000 ALTER TABLE `act_ru_timer_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_timer_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_variable`
--

DROP TABLE IF EXISTS `act_ru_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_variable`
--

LOCK TABLES `act_ru_variable` WRITE;
/*!40000 ALTER TABLE `act_ru_variable` DISABLE KEYS */;
INSERT INTO `act_ru_variable` VALUES ('15002',1,'string','processDefinitionId','15001','15001',NULL,NULL,NULL,NULL,'process_5:1:11',NULL),('15003',1,'long','businessKey','15001','15001',NULL,NULL,NULL,6,'6',NULL),('15004',1,'string','userid','15001','15001',NULL,NULL,NULL,NULL,'admin',NULL),('17502',1,'string','processDefinitionId','17501','17501',NULL,NULL,NULL,NULL,'process_5:1:11',NULL),('17503',1,'long','businessKey','17501','17501',NULL,NULL,NULL,7,'7',NULL),('17504',1,'string','userid','17501','17501',NULL,NULL,NULL,NULL,'admin',NULL),('27506',1,'string','processDefinitionId','27505','27505',NULL,NULL,NULL,NULL,'process_5:1:11',NULL),('27507',1,'long','businessKey','27505','27505',NULL,NULL,NULL,8,'8',NULL),('27508',1,'string','userid','27505','27505',NULL,NULL,NULL,NULL,'admin',NULL),('30002',1,'string','processDefinitionId','30001','30001',NULL,NULL,NULL,NULL,'process_5:1:11',NULL),('30003',1,'long','businessKey','30001','30001',NULL,NULL,NULL,9,'9',NULL),('30004',1,'string','userid','30001','30001',NULL,NULL,NULL,NULL,'admin',NULL);
/*!40000 ALTER TABLE `act_ru_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2022-04-28 09:46:38',1,'EXECUTED','8:ee6ac84cdbeb19acaf73c39298fb40f8','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'4.6.1',NULL,NULL,'1110392312'),('20220428081257-1','jhipster','config/liquibase/changelog/20220428081257_added_entity_Menu.xml','2022-04-28 16:19:20',2,'EXECUTED','8:78a95988dd89eb412aa72c226b0c6925','createTable tableName=sys_menu','',NULL,'4.6.1',NULL,NULL,'1133959792'),('20220428081257-1-data','jhipster','config/liquibase/changelog/20220428081257_added_entity_Menu.xml','2022-04-28 16:19:20',3,'EXECUTED','8:01a6ff240d4bb49283d3de14ba345521','loadData tableName=sys_menu','',NULL,'4.6.1','faker',NULL,'1133959792'),('20220503083341-1','jhipster','config/liquibase/changelog/20220503083341_added_entity_UiComponent.xml','2022-05-03 16:57:04',4,'EXECUTED','8:d1a8ed54189536ab5e60623a2f7133b4','createTable tableName=sys_uicomponent','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083341-1-data','jhipster','config/liquibase/changelog/20220503083341_added_entity_UiComponent.xml','2022-05-03 16:57:05',5,'EXECUTED','8:14e127fab9099393cf8b6922a14251a4','loadData tableName=sys_uicomponent','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083342-1','jhipster','config/liquibase/changelog/20220503083342_added_entity_UiToolButton.xml','2022-05-03 16:57:05',6,'EXECUTED','8:a00984989faaa13d01c05329e5eeb066','createTable tableName=sys_uitoolbutton','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083342-1-data','jhipster','config/liquibase/changelog/20220503083342_added_entity_UiToolButton.xml','2022-05-03 16:57:05',7,'EXECUTED','8:cf6d3cd718598305ac4040c85d2fb4de','loadData tableName=sys_uitoolbutton','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083343-1','jhipster','config/liquibase/changelog/20220503083343_added_entity_UiTable.xml','2022-05-03 16:57:06',8,'EXECUTED','8:6c227879eaf083133eff7e28aaa0a7f5','createTable tableName=sys_uitable','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083343-1-data','jhipster','config/liquibase/changelog/20220503083343_added_entity_UiTable.xml','2022-05-03 16:57:06',9,'EXECUTED','8:65b8683246ea12a32062164d3831d2fa','loadData tableName=sys_uitable','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083344-1','jhipster','config/liquibase/changelog/20220503083344_added_entity_UiEditform.xml','2022-05-03 16:57:06',10,'EXECUTED','8:6e35bb04e028f4b22c5ceb841f3cb7ce','createTable tableName=sys_uieditform','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083344-1-data','jhipster','config/liquibase/changelog/20220503083344_added_entity_UiEditform.xml','2022-05-03 16:57:06',11,'EXECUTED','8:9ce4fb7f8f075080a0e133a4c45bf027','loadData tableName=sys_uieditform','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083345-1','jhipster','config/liquibase/changelog/20220503083345_added_entity_UiQueryform.xml','2022-05-03 16:57:07',12,'EXECUTED','8:b185356129014a88b858dcacc58cbb97','createTable tableName=sys_uiqueryform','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083345-1-data','jhipster','config/liquibase/changelog/20220503083345_added_entity_UiQueryform.xml','2022-05-03 16:57:07',13,'EXECUTED','8:7d9d176176c1403be896486498104cda','loadData tableName=sys_uiqueryform','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220504121625-1','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-04 20:34:55',14,'EXECUTED','8:b2cbe3b600976fb47508ded908be3693','createTable tableName=t_example','',NULL,'4.6.1',NULL,NULL,'1667694945'),('20220504121625-1-data','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-04 20:34:55',15,'EXECUTED','8:69f7e341c65f756008df04426334f341','loadData tableName=t_example','',NULL,'4.6.1','faker',NULL,'1667694945'),('20220504123346-1','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-05 16:59:46',16,'EXECUTED','8:b2cbe3b600976fb47508ded908be3693','createTable tableName=t_example','',NULL,'4.6.1',NULL,NULL,'1741186154'),('20220504123346-1-data','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-05 16:59:46',17,'EXECUTED','8:69f7e341c65f756008df04426334f341','loadData tableName=t_example','',NULL,'4.6.1','faker',NULL,'1741186154'),('20220507141955-1','jhipster','config/liquibase/changelog/20220507141955_added_entity_LeaveSlip.xml','2022-05-07 22:22:27',18,'EXECUTED','8:5933d84eecf76ca243e4a975ba7397ea','createTable tableName=t_leaveslip','',NULL,'4.6.1',NULL,NULL,'1933346695'),('20220507141955-1-data','jhipster','config/liquibase/changelog/20220507141955_added_entity_LeaveSlip.xml','2022-05-07 22:22:27',19,'EXECUTED','8:c2853df03bebde01bae62dc285eacc1a','loadData tableName=t_leaveslip','',NULL,'4.6.1','faker',NULL,'1933346695'),('20220508030550-1','jhipster','config/liquibase/changelog/20220508030550_added_entity_LeaveType.xml','2022-05-08 11:10:16',20,'EXECUTED','8:b9841d88feb49a742bea4ddfef2a9d3c','createTable tableName=ele_leavetype','',NULL,'4.6.1',NULL,NULL,'1979416033'),('20220508030550-1-data','jhipster','config/liquibase/changelog/20220508030550_added_entity_LeaveType.xml','2022-05-08 11:10:16',21,'EXECUTED','8:9c60885e7adcd1f1cb40c0b7985f2f9c','loadData tableName=ele_leavetype','',NULL,'4.6.1','faker',NULL,'1979416033'),('20220510065853-1','jhipster','config/liquibase/changelog/20220510065853_added_entity_UiTab.xml','2022-05-10 15:03:00',22,'EXECUTED','8:54625e5965044bf9c5e005b4ef9e017e','createTable tableName=sys_uitab','',NULL,'4.6.1',NULL,NULL,'2166179798'),('20220510065853-1-data','jhipster','config/liquibase/changelog/20220510065853_added_entity_UiTab.xml','2022-05-10 15:03:00',23,'EXECUTED','8:638d93222d02102a1b36418cdb948295','loadData tableName=sys_uitab','',NULL,'4.6.1','faker',NULL,'2166179798'),('20220514181352-1','jhipster','config/liquibase/changelog/20220514181352_added_entity_DataPermission.xml','2022-05-15 02:16:14',24,'EXECUTED','8:47c14f534ecd78551f13f641f004da6a','createTable tableName=sys_data_permissions','',NULL,'4.6.1',NULL,NULL,'2552173977'),('20220514181352-1-data','jhipster','config/liquibase/changelog/20220514181352_added_entity_DataPermission.xml','2022-05-15 02:16:14',25,'EXECUTED','8:73f5903c34625de2687a42c74d8c35dd','loadData tableName=sys_data_permissions','',NULL,'4.6.1','faker',NULL,'2552173977'),('20220515070013-1','jhipster','config/liquibase/changelog/20220515070013_added_entity_DataPermissionDetails.xml','2022-05-15 15:01:57',26,'EXECUTED','8:39de15badf2acd6b0dafd4b97e842ee6','createTable tableName=sys_data_permission_details','',NULL,'4.6.1',NULL,NULL,'2598116848'),('20220515070013-1-data','jhipster','config/liquibase/changelog/20220515070013_added_entity_DataPermissionDetails.xml','2022-05-15 15:01:57',27,'EXECUTED','8:bb5d6ff1f0f2a34e428bdb56e3414555','loadData tableName=sys_data_permission_details','',NULL,'4.6.1','faker',NULL,'2598116848'),('20220515082952-1','jhipster','config/liquibase/changelog/20220515082952_added_entity_DataPermissionsRel.xml','2022-05-15 16:34:37',28,'EXECUTED','8:0967b6172cf96b7074fa4e36d40eac12','createTable tableName=sys_data_permissions_rel','',NULL,'4.6.1',NULL,NULL,'2603677272'),('20220515082952-1-data','jhipster','config/liquibase/changelog/20220515082952_added_entity_DataPermissionsRel.xml','2022-05-15 16:34:38',29,'EXECUTED','8:13a9128d07f5cf2fb70f01c2a4decde8','loadData tableName=sys_data_permissions_rel','',NULL,'4.6.1','faker',NULL,'2603677272'),('20220515130727-1','jhipster','config/liquibase/changelog/20220515130727_added_entity_RoleMenu.xml','2022-05-15 21:10:01',30,'EXECUTED','8:384441a05500e8c6201ac9794b03ce0d','createTable tableName=sys_role_menu','',NULL,'4.6.1',NULL,NULL,'2620201300'),('20220515130727-1-data','jhipster','config/liquibase/changelog/20220515130727_added_entity_RoleMenu.xml','2022-05-15 21:10:02',31,'EXECUTED','8:dbf7241664a36220ed4d35e0b8c82e68','loadData tableName=sys_role_menu','',NULL,'4.6.1','faker',NULL,'2620201300'),('20220515132234-1','jhipster','config/liquibase/changelog/20220515132234_added_entity_RoleMenuToolButton.xml','2022-05-15 21:24:25',32,'EXECUTED','8:3c91410f0c2c3a1897c29583c5acf242','createTable tableName=sys_role_menu_button','',NULL,'4.6.1',NULL,NULL,'2621064678'),('20220515132234-1-data','jhipster','config/liquibase/changelog/20220515132234_added_entity_RoleMenuToolButton.xml','2022-05-15 21:24:25',33,'EXECUTED','8:1de8db3ef0fd257cd180252f7f6aac89','loadData tableName=sys_role_menu_button','',NULL,'4.6.1','faker',NULL,'2621064678');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ele_leavetype`
--

DROP TABLE IF EXISTS `ele_leavetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ele_leavetype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parentid` bigint(20) DEFAULT NULL COMMENT 'select 是不需要parentid的，为要素表ele_统一增加该字段',
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='请假类型\\n\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ele_leavetype`
--

LOCK TABLES `ele_leavetype` WRITE;
/*!40000 ALTER TABLE `ele_leavetype` DISABLE KEYS */;
INSERT INTO `ele_leavetype` VALUES (11,'sick-leave','病假',0,''),(12,'casual-leave','事假',0,''),(13,'marriage-leave','婚假',0,'');
/*!40000 ALTER TABLE `ele_leavetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image_url` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `activation_key` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reset_key` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_date` timestamp NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','','zh-cn',NULL,NULL,'system',NULL,NULL,'system',NULL),(2,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','','zh-cn',NULL,NULL,'system',NULL,NULL,'system',NULL);
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(1,'ROLE_USER'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_permission_details`
--

DROP TABLE IF EXISTS `sys_data_permission_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_permission_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限名称',
  `left_bracket` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '左括号',
  `jhi_column` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `op` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `right_bracket` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '右括号',
  `ordernum` int(11) DEFAULT NULL,
  `logical_rel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='数据权限明细\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_permission_details`
--

LOCK TABLES `sys_data_permission_details` WRITE;
/*!40000 ALTER TABLE `sys_data_permission_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_data_permission_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_permissions`
--

DROP TABLE IF EXISTS `sys_data_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限编码',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限名称',
  `rule_sql` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '规则sql, 配置明细时候才生成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='数据权限主表\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_permissions`
--

LOCK TABLES `sys_data_permissions` WRITE;
/*!40000 ALTER TABLE `sys_data_permissions` DISABLE KEYS */;
INSERT INTO `sys_data_permissions` VALUES (1,'all_data_permission','全部数据权限',NULL),(2,'institution_data_permission','机构数据权限',NULL),(3,'institution_with_following_data_permission','机构及以下数据权限',NULL),(4,'personal_only_data_permission','仅本人数据权限',NULL),(5,'','测试动态权限','name=\'张三\'');
/*!40000 ALTER TABLE `sys_data_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_permissions_rel`
--

DROP TABLE IF EXISTS `sys_data_permissions_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_permissions_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限id',
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色, 菜单, 数据权限关系\\n角色能看得所有菜单不一定都要有数据权限, 但是只有能看到菜单才能去配置权限\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_permissions_rel`
--

LOCK TABLES `sys_data_permissions_rel` WRITE;
/*!40000 ALTER TABLE `sys_data_permissions_rel` DISABLE KEYS */;
INSERT INTO `sys_data_permissions_rel` VALUES (6,'1','ROLE_ADMIN','6'),(7,'5','ROLE_ADMIN','4');
/*!40000 ALTER TABLE `sys_data_permissions_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `icon_cls` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keep_alive` bit(1) DEFAULT NULL,
  `require_auth` bit(1) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,NULL,'首页','el-icon-s-home','','\0',0,'','{}',1,'/'),(2,NULL,'示例菜单','el-icon-share','\0','',0,'','{}',2,'#'),(3,NULL,'演示-HelloWorld','el-icon-share','\0','\0',2,'','{component:\"example/hello-world\"}',3,'/example/helloworld'),(4,NULL,'演示-动态界面','el-icon-star-on','\0','\0',2,'','{component:\"example/uiexample\"}',4,'/example/uiexample'),(5,NULL,'演示-请假单录入','el-icon-share','\0','\0',2,'','{component:\"example/leaveslip-add\"}',5,'/example/leaveslip/add'),(6,NULL,'演示-请假单审核','el-icon-share','\0','\0',2,'','{component:\"example/leaveslip-audit\"}',6,'/example/leaveslip/audit'),(7,NULL,'菜单管理','el-icon-s-data','\0','\0',0,'','{}',7,'#1'),(8,NULL,'基础要素维护','el-icon-s-data','\0','\0',0,'','{}',8,'#'),(9,NULL,'菜单维护',NULL,'\0','\0',7,'','{}',9,'/menu/'),(10,NULL,'组件配置',NULL,'\0','\0',7,'','{}',10,'/ui-component/'),(11,NULL,'按钮配置',NULL,'\0','\0',7,'','{}',11,'/ui-tool-button/'),(12,NULL,'列表配置',NULL,'\0','\0',7,'','{}',12,'/ui-table/'),(13,NULL,'编辑区配置',NULL,'\0','\0',7,'','{}',13,'/ui-editform/'),(14,NULL,'查询区配置',NULL,'\0','\0',7,'','{}',14,'/ui-queryform/'),(15,NULL,'页签区配置',NULL,'\0','\0',7,'','{}',15,'/ui-tab/'),(16,NULL,'请假类型维护',NULL,'\0','\0',8,'','{}',16,'/leave-type/'),(17,NULL,'权限管理','el-icon-user-solid','\0','\0',0,'','{}',17,'#'),(18,NULL,'数据权限维护',NULL,'\0','\0',17,'','{}',18,'/data-permission/'),(19,NULL,'数据权限分配',NULL,'\0','\0',17,'','{component:\"permission/data-permission\"}',19,'/sys/data-permission/create'),(20,NULL,'功能权限分配',NULL,'\0','\0',17,'','{component:\"permission/tool-button-permission\"}',20,'/sys/tool-button-permission/create'),(21,NULL,'大屏管理','el-icon-s-platform','\0','\0',0,'','{}',21,'#'),(22,NULL,'系统配置','el-icon-setting','\0','\0',0,'','{}',22,'#'),(23,NULL,'系统参数配置',NULL,'\0','\0',22,'','{}',23,'#'),(24,NULL,'定时任务配置',NULL,'\0','\0',22,'','{}',24,'#'),(25,NULL,'仪表盘组件维护',NULL,'\0','\0',21,'','{}',25,'#'),(26,NULL,'仪表盘组件配置',NULL,'\0','\0',21,'','{}',26,'#'),(27,NULL,'系统监控','el-icon-camera-solid','\0','\0',0,'','{}',27,'#'),(28,NULL,'在线人员监控',NULL,'\0','\0',27,'','{}',28,'#'),(29,NULL,'操作日志监控',NULL,'\0','\0',27,'','{}',29,'#'),(30,NULL,'采集表定义',NULL,'\0','\0',22,'','{}',30,'#'),(31,NULL,'菜单角色权限分配',NULL,'\0','\0',17,'','{component:\"permission/role-menu\"}',31,'/sys/role-menu/create');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色对菜单\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (187,'ROLE_ADMIN','1'),(188,'ROLE_ADMIN','2'),(189,'ROLE_ADMIN','3'),(190,'ROLE_ADMIN','4'),(191,'ROLE_ADMIN','5'),(192,'ROLE_ADMIN','6'),(193,'ROLE_ADMIN','7'),(194,'ROLE_ADMIN','9'),(195,'ROLE_ADMIN','10'),(196,'ROLE_ADMIN','11'),(197,'ROLE_ADMIN','12'),(198,'ROLE_ADMIN','13'),(199,'ROLE_ADMIN','14'),(200,'ROLE_ADMIN','15'),(201,'ROLE_ADMIN','8'),(202,'ROLE_ADMIN','16'),(203,'ROLE_ADMIN','17'),(204,'ROLE_ADMIN','18'),(205,'ROLE_ADMIN','19'),(206,'ROLE_ADMIN','20'),(207,'ROLE_ADMIN','31'),(208,'ROLE_ADMIN','21'),(209,'ROLE_ADMIN','25'),(210,'ROLE_ADMIN','26'),(211,'ROLE_ADMIN','22'),(212,'ROLE_ADMIN','23'),(213,'ROLE_ADMIN','24'),(214,'ROLE_ADMIN','30'),(215,'ROLE_ADMIN','27'),(216,'ROLE_ADMIN','28'),(217,'ROLE_ADMIN','29'),(218,'ROLE_USER','1'),(219,'ROLE_USER','2'),(220,'ROLE_USER','3'),(221,'ROLE_USER','4'),(222,'ROLE_USER','5'),(223,'ROLE_USER','6');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu_button`
--

DROP TABLE IF EXISTS `sys_role_menu_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu_button` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单id',
  `tool_button_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色,菜单,按钮权限\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu_button`
--

LOCK TABLES `sys_role_menu_button` WRITE;
/*!40000 ALTER TABLE `sys_role_menu_button` DISABLE KEYS */;
INSERT INTO `sys_role_menu_button` VALUES (4,'ROLE_ADMIN','6',15);
/*!40000 ALTER TABLE `sys_role_menu_button` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uicomponent`
--

DROP TABLE IF EXISTS `sys_uicomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uicomponent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `componentid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uicomponent`
--

LOCK TABLES `sys_uicomponent` WRITE;
/*!40000 ALTER TABLE `sys_uicomponent` DISABLE KEYS */;
INSERT INTO `sys_uicomponent` VALUES (11,0,1,'uieditform','{}'),(12,0,2,'uitoolbutton','{}'),(13,0,4,'uitable','{}'),(14,5,1,'uieditform','{isinline:false}'),(15,5,2,'uitoolbutton','{}'),(16,6,1,'uitoolbutton','{}'),(17,6,2,'uitable','{}'),(18,0,3,'uitab','{}');
/*!40000 ALTER TABLE `sys_uicomponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uieditform`
--

DROP TABLE IF EXISTS `sys_uieditform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uieditform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `issource` bit(1) DEFAULT NULL,
  `isedit` bit(1) DEFAULT NULL,
  `requirement` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `placeholder` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uieditform`
--

LOCK TABLES `sys_uieditform` WRITE;
/*!40000 ALTER TABLE `sys_uieditform` DISABLE KEYS */;
INSERT INTO `sys_uieditform` VALUES (12,0,'name','姓名',1,'\0','','','input','请输入姓名','{}'),(13,0,'sex','性别',2,'\0','','','input','请输入性别','{}'),(14,0,'age','年龄',3,'\0','','','input','请输入年龄','{}'),(15,5,'type','请假类型',1,'','','','select','请选择','{eleBeanName:\"leaveTypeRepository\"}'),(16,5,'startTime','开始时间',2,'\0','','\0','date','请选择开始时间','{}'),(17,5,'endTime','结束时间',3,'\0','','\0','date','请选择结束时间','{}'),(18,5,'reason','请假事由',4,'\0','','\0','textarea','请输入请假事由','{}'),(19,5,'file','说明附件',5,'\0','','\0','input','请选择附件','{}'),(20,5,'superior','直接上级',6,'','','\0','select','请选择','{eleBeanName:\"userRepository\"}');
/*!40000 ALTER TABLE `sys_uieditform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uiqueryform`
--

DROP TABLE IF EXISTS `sys_uiqueryform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uiqueryform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `issource` bit(1) DEFAULT NULL,
  `requirement` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `placeholder` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uiqueryform`
--

LOCK TABLES `sys_uiqueryform` WRITE;
/*!40000 ALTER TABLE `sys_uiqueryform` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_uiqueryform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uitab`
--

DROP TABLE IF EXISTS `sys_uitab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uitab` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页签的显示编码, 唯一,根据code业务做自己的处理',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '页签显示的标题',
  `ordernum` int(11) DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='页签配置\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uitab`
--

LOCK TABLES `sys_uitab` WRITE;
/*!40000 ALTER TABLE `sys_uitab` DISABLE KEYS */;
INSERT INTO `sys_uitab` VALUES (11,0,'willaudit','待办',1,'{}'),(12,0,'audited','已办',2,'{}');
/*!40000 ALTER TABLE `sys_uitab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uitable`
--

DROP TABLE IF EXISTS `sys_uitable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uitable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `issource` bit(1) DEFAULT NULL,
  `isedit` bit(1) DEFAULT NULL,
  `requirement` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uitable`
--

LOCK TABLES `sys_uitable` WRITE;
/*!40000 ALTER TABLE `sys_uitable` DISABLE KEYS */;
INSERT INTO `sys_uitable` VALUES (11,0,'name','姓名',1,'\0','\0','','input','{}'),(12,0,'age','年龄',2,'\0','','\0','input','{}'),(13,0,'sex','性别',3,'\0','','','input','{}'),(14,6,'type','请假类型',1,'','\0','\0','input','{}'),(15,6,'startTime','开始时间',2,'\0','\0','\0','date','{}'),(16,6,'endTime','结束时间',3,'\0','\0','\0','date','{}'),(17,6,'leavePerson','请假人',4,'\0','\0','\0','input','{}'),(18,6,'wfstatus','状态',5,'\0','\0','\0','input','{}');
/*!40000 ALTER TABLE `sys_uitable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uitoolbutton`
--

DROP TABLE IF EXISTS `sys_uitoolbutton`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uitoolbutton` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `action` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uitoolbutton`
--

LOCK TABLES `sys_uitoolbutton` WRITE;
/*!40000 ALTER TABLE `sys_uitoolbutton` DISABLE KEYS */;
INSERT INTO `sys_uitoolbutton` VALUES (11,0,'save','保存',1,'save','{}'),(13,0,'delete','删除',2,'delete','{}'),(14,5,'save','提交',1,'save','{}'),(15,6,'audit','审核',1,'audit','{}'),(16,6,'auditInfo','审核情况',2,'auditInfo','{}');
/*!40000 ALTER TABLE `sys_uitoolbutton` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_example`
--

DROP TABLE IF EXISTS `t_example`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_example` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_example`
--

LOCK TABLES `t_example` WRITE;
/*!40000 ALTER TABLE `t_example` DISABLE KEYS */;
INSERT INTO `t_example` VALUES (11,'张三',30,'男'),(12,'李四',99,'男'),(13,'测试1',11,'11'),(14,'测试2',11,'12'),(15,'测试2',11,'12'),(16,'测试2',11,'12'),(17,'测试2',11,'12'),(18,'测试2',11,'12'),(19,'测试',1,'1'),(20,'测试',1,'1'),(21,'测试',1,'1'),(22,'测试',1,'1'),(23,'111',111,'111'),(24,'111',111,'111'),(25,'111',111,'111'),(32,'测试1',11,'11'),(33,'测试1',11,'11'),(34,'测试1',11,'11'),(35,'测试1',11,'11');
/*!40000 ALTER TABLE `t_example` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_leaveslip`
--

DROP TABLE IF EXISTS `t_leaveslip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_leaveslip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `end_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `superior` bigint(20) DEFAULT NULL,
  `wfstatus` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `leave_person` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_leaveslip`
--

LOCK TABLES `t_leaveslip` WRITE;
/*!40000 ALTER TABLE `t_leaveslip` DISABLE KEYS */;
INSERT INTO `t_leaveslip` VALUES (6,'11','2022-05-01','2022-05-01','测试请假','/tmp/11.pdf',1,'011',NULL),(7,'12','2022-05-01','2022-05-03','请个事假','/tmp/11.pdf',1,'011','admin'),(8,'13','2022-05-01','2022-05-08','测试婚假','11',1,'011','admin'),(9,'12','2022-05-01','2022-05-08','测试事假','11',2,'011','admin');
/*!40000 ALTER TABLE `t_leaveslip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-21 10:07:56
