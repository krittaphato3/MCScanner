package org.krittaphato3.mcping.model;



@javax.annotation.processing.Generated("dsl_json")
public class _ServerResponse_DslJsonConverter implements com.dslplatform.json.Configuration {
	private static final java.nio.charset.Charset utf8 = java.nio.charset.Charset.forName("UTF-8");
	@Override
	public void configure(com.dslplatform.json.DslJson __dsljson) {
		ObjectFormatConverter objectConverter = new ObjectFormatConverter(__dsljson);
		__dsljson.registerReader(org.krittaphato3.mcping.model.ServerResponse.class, objectConverter);
		__dsljson.registerWriter(org.krittaphato3.mcping.model.ServerResponse.class, objectConverter);
	}
	public final static class ObjectFormatConverter implements com.dslplatform.json.runtime.FormatConverter<org.krittaphato3.mcping.model.ServerResponse> {
		private final boolean alwaysSerialize;
		private final com.dslplatform.json.DslJson __dsljson;
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.Players> reader_players;
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.Players> reader_players() {
			if (reader_players == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.Players.class;
				reader_players = __dsljson.tryFindReader(manifest);
				if (reader_players == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find reader for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return reader_players;
		}
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.Players> writer_players;
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.Players> writer_players() {
			if (writer_players == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.Players.class;
				writer_players = __dsljson.tryFindWriter(manifest);
				if (writer_players == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find writer for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return writer_players;
		}
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.Description> reader_description;
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.Description> reader_description() {
			if (reader_description == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.Description.class;
				reader_description = __dsljson.tryFindReader(manifest);
				if (reader_description == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find reader for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return reader_description;
		}
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.Description> writer_description;
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.Description> writer_description() {
			if (writer_description == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.Description.class;
				writer_description = __dsljson.tryFindWriter(manifest);
				if (writer_description == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find writer for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return writer_description;
		}
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.Version> reader_version;
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.Version> reader_version() {
			if (reader_version == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.Version.class;
				reader_version = __dsljson.tryFindReader(manifest);
				if (reader_version == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find reader for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return reader_version;
		}
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.Version> writer_version;
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.Version> writer_version() {
			if (writer_version == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.Version.class;
				writer_version = __dsljson.tryFindWriter(manifest);
				if (writer_version == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find writer for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return writer_version;
		}
		public ObjectFormatConverter(com.dslplatform.json.DslJson __dsljson) {
			this.alwaysSerialize = !__dsljson.omitDefaults;
			this.__dsljson = __dsljson;
		}
		private static final byte[] quoted_version = "\"version\":".getBytes(utf8);
		private static final byte[] name_version = "version".getBytes(utf8);
		private static final byte[] quoted_players = ",\"players\":".getBytes(utf8);
		private static final byte[] name_players = "players".getBytes(utf8);
		private static final byte[] quoted_description = ",\"description\":".getBytes(utf8);
		private static final byte[] name_description = "description".getBytes(utf8);
		private static final byte[] quoted_favicon = ",\"favicon\":".getBytes(utf8);
		private static final byte[] name_favicon = "favicon".getBytes(utf8);
		private static final byte[] quoted_previewChat = ",\"previewChat\":".getBytes(utf8);
		private static final byte[] name_previewChat = "previewChat".getBytes(utf8);
		private static final byte[] quoted_enforcesSecureChat = ",\"enforcesSecureChat\":".getBytes(utf8);
		private static final byte[] name_enforcesSecureChat = "enforcesSecureChat".getBytes(utf8);
		public final void write(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.ServerResponse instance) {
			if (instance == null) writer.writeNull();
			else {
				writer.writeByte((byte)'{');
				if (alwaysSerialize) { writeContentFull(writer, instance); writer.writeByte((byte)'}'); }
				else if (writeContentMinimal(writer, instance)) writer.getByteBuffer()[writer.size() - 1] = '}';
				else writer.writeByte((byte)'}');
			}
		}
		public void writeContentFull(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.ServerResponse instance) {
			writer.writeAscii(quoted_version);
			if (instance.getVersion() == null) writer.writeNull();
			else writer_version().write(writer, instance.getVersion());
			writer.writeAscii(quoted_players);
			if (instance.getPlayers() == null) writer.writeNull();
			else writer_players().write(writer, instance.getPlayers());
			writer.writeAscii(quoted_description);
			if (instance.getDescription() == null) writer.writeNull();
			else writer_description().write(writer, instance.getDescription());
			writer.writeAscii(quoted_favicon);
			if (instance.getFavicon() == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.getFavicon(), writer);
			writer.writeAscii(quoted_previewChat);
			com.dslplatform.json.BoolConverter.serialize(instance.isPreviewChat(), writer);
			writer.writeAscii(quoted_enforcesSecureChat);
			com.dslplatform.json.BoolConverter.serialize(instance.isEnforcesSecureChat(), writer);
		}
		public boolean writeContentMinimal(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.ServerResponse instance) {
			boolean hasWritten = false;
			if (instance.getVersion() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_version); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				writer_version().write(writer, instance.getVersion());
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.getPlayers() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_players); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				writer_players().write(writer, instance.getPlayers());
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.getDescription() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_description); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				writer_description().write(writer, instance.getDescription());
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.getFavicon() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_favicon); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.getFavicon(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.isPreviewChat() != false) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_previewChat); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.BoolConverter.serialize(instance.isPreviewChat(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.isEnforcesSecureChat() != false) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_enforcesSecureChat); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.BoolConverter.serialize(instance.isEnforcesSecureChat(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			return hasWritten;
		}
		public org.krittaphato3.mcping.model.ServerResponse read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if (reader.wasNull()) return null;
			else if (reader.last() != '{') throw reader.newParseError("Expecting '{' for object start");
			reader.getNextToken();
			return readContent(reader);
		}
		public org.krittaphato3.mcping.model.ServerResponse readContent(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			org.krittaphato3.mcping.model.Version _version_ = null;
			org.krittaphato3.mcping.model.Players _players_ = null;
			org.krittaphato3.mcping.model.Description _description_ = null;
			java.lang.String _favicon_ = null;
			boolean _previewChat_ = false;
			boolean _enforcesSecureChat_ = false;
			if (reader.last() == '}') {
				return new org.krittaphato3.mcping.model.ServerResponse(_version_, _players_, _description_, _favicon_, _previewChat_, _enforcesSecureChat_);
			}
			switch(reader.fillName()) {
				case 348833321:
					reader.getNextToken();
					_previewChat_ = com.dslplatform.json.BoolConverter.deserialize(reader);
					reader.getNextToken();
					break;
				case -1814655819:
					reader.getNextToken();
					_enforcesSecureChat_ = com.dslplatform.json.BoolConverter.deserialize(reader);
					reader.getNextToken();
					break;
				case 895577559:
					reader.getNextToken();
					_favicon_ = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				case -1458655735:
					reader.getNextToken();
					_players_ = reader_players().read(reader);
					reader.getNextToken();
					break;
				case 879704937:
					reader.getNextToken();
					_description_ = reader_description().read(reader);
					reader.getNextToken();
					break;
				case 1181855383:
					reader.getNextToken();
					_version_ = reader_version().read(reader);
					reader.getNextToken();
					break;
				default:
					reader.getNextToken();
					reader.skip();
			}
			while (reader.last() == ','){
				reader.getNextToken();
				switch(reader.fillName()) {
					case 348833321:
						reader.getNextToken();
						_previewChat_ = com.dslplatform.json.BoolConverter.deserialize(reader);
						reader.getNextToken();
						break;
					case -1814655819:
						reader.getNextToken();
						_enforcesSecureChat_ = com.dslplatform.json.BoolConverter.deserialize(reader);
						reader.getNextToken();
						break;
					case 895577559:
						reader.getNextToken();
						_favicon_ = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					case -1458655735:
						reader.getNextToken();
						_players_ = reader_players().read(reader);
						reader.getNextToken();
						break;
					case 879704937:
						reader.getNextToken();
						_description_ = reader_description().read(reader);
						reader.getNextToken();
						break;
					case 1181855383:
						reader.getNextToken();
						_version_ = reader_version().read(reader);
						reader.getNextToken();
						break;
					default:
						reader.getNextToken();
						reader.skip();
				}
			}
			if (reader.last() != '}') throw reader.newParseError("Expecting '}' for object end");
			return new org.krittaphato3.mcping.model.ServerResponse(_version_, _players_, _description_, _favicon_, _previewChat_, _enforcesSecureChat_);
		}
	}
}
