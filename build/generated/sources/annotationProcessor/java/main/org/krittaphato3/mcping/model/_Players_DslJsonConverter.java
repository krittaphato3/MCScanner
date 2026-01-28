package org.krittaphato3.mcping.model;



@javax.annotation.processing.Generated("dsl_json")
public class _Players_DslJsonConverter implements com.dslplatform.json.Configuration {
	private static final java.nio.charset.Charset utf8 = java.nio.charset.Charset.forName("UTF-8");
	@Override
	public void configure(com.dslplatform.json.DslJson __dsljson) {
		ObjectFormatConverter objectConverter = new ObjectFormatConverter(__dsljson);
		__dsljson.registerReader(org.krittaphato3.mcping.model.Players.class, objectConverter);
		__dsljson.registerWriter(org.krittaphato3.mcping.model.Players.class, objectConverter);
	}
	public final static class ObjectFormatConverter implements com.dslplatform.json.runtime.FormatConverter<org.krittaphato3.mcping.model.Players> {
		private final boolean alwaysSerialize;
		private final com.dslplatform.json.DslJson __dsljson;
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.Player> reader_sample;
		private com.dslplatform.json.JsonReader.ReadObject<org.krittaphato3.mcping.model.Player> reader_sample() {
			if (reader_sample == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.Player.class;
				reader_sample = __dsljson.tryFindReader(manifest);
				if (reader_sample == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find reader for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return reader_sample;
		}
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.Player> writer_sample;
		private com.dslplatform.json.JsonWriter.WriteObject<org.krittaphato3.mcping.model.Player> writer_sample() {
			if (writer_sample == null) {
				java.lang.reflect.Type manifest = org.krittaphato3.mcping.model.Player.class;
				writer_sample = __dsljson.tryFindWriter(manifest);
				if (writer_sample == null) {
					throw new com.dslplatform.json.ConfigurationException("Unable to find writer for " + manifest + ". Enable runtime conversion by initializing DslJson with new DslJson<>(Settings.basicSetup())");
				}
			}
			return writer_sample;
		}
		public ObjectFormatConverter(com.dslplatform.json.DslJson __dsljson) {
			this.alwaysSerialize = !__dsljson.omitDefaults;
			this.__dsljson = __dsljson;
		}
		private static final byte[] quoted_max = "\"max\":".getBytes(utf8);
		private static final byte[] name_max = "max".getBytes(utf8);
		private static final byte[] quoted_online = ",\"online\":".getBytes(utf8);
		private static final byte[] name_online = "online".getBytes(utf8);
		private static final byte[] quoted_sample = ",\"sample\":".getBytes(utf8);
		private static final byte[] name_sample = "sample".getBytes(utf8);
		public final void write(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Players instance) {
			if (instance == null) writer.writeNull();
			else {
				writer.writeByte((byte)'{');
				if (alwaysSerialize) { writeContentFull(writer, instance); writer.writeByte((byte)'}'); }
				else if (writeContentMinimal(writer, instance)) writer.getByteBuffer()[writer.size() - 1] = '}';
				else writer.writeByte((byte)'}');
			}
		}
		public void writeContentFull(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Players instance) {
			writer.writeAscii(quoted_max);
			com.dslplatform.json.NumberConverter.serialize(instance.getMax(), writer);
			writer.writeAscii(quoted_online);
			com.dslplatform.json.NumberConverter.serialize(instance.getOnline(), writer);
			writer.writeAscii(quoted_sample);
			if (instance.getSample() == null) writer.writeNull();
			else writer.serialize(instance.getSample(), writer_sample());
		}
		public boolean writeContentMinimal(final com.dslplatform.json.JsonWriter writer, final org.krittaphato3.mcping.model.Players instance) {
			boolean hasWritten = false;
			if (instance.getMax() != 0) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_max); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.NumberConverter.serialize(instance.getMax(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.getOnline() != 0) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_online); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.NumberConverter.serialize(instance.getOnline(), writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.getSample() != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_sample); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				writer.serialize(instance.getSample(), writer_sample());
				writer.writeByte((byte)','); hasWritten = true;
			}
			return hasWritten;
		}
		public org.krittaphato3.mcping.model.Players read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if (reader.wasNull()) return null;
			else if (reader.last() != '{') throw reader.newParseError("Expecting '{' for object start");
			reader.getNextToken();
			return readContent(reader);
		}
		public org.krittaphato3.mcping.model.Players readContent(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			int _max_ = 0;
			int _online_ = 0;
			java.util.List<org.krittaphato3.mcping.model.Player> _sample_ = null;
			if (reader.last() == '}') {
				return new org.krittaphato3.mcping.model.Players(_max_, _online_, _sample_);
			}
			switch(reader.fillName()) {
				case -677190887:
					reader.getNextToken();
					_max_ = com.dslplatform.json.NumberConverter.deserializeInt(reader);
					reader.getNextToken();
					break;
				case -1888276342:
					reader.getNextToken();
					_online_ = com.dslplatform.json.NumberConverter.deserializeInt(reader);
					reader.getNextToken();
					break;
				case -1763474777:
					reader.getNextToken();
					_sample_ = reader.readCollection(reader_sample());
					reader.getNextToken();
					break;
				default:
					reader.getNextToken();
					reader.skip();
			}
			while (reader.last() == ','){
				reader.getNextToken();
				switch(reader.fillName()) {
					case -677190887:
						reader.getNextToken();
						_max_ = com.dslplatform.json.NumberConverter.deserializeInt(reader);
						reader.getNextToken();
						break;
					case -1888276342:
						reader.getNextToken();
						_online_ = com.dslplatform.json.NumberConverter.deserializeInt(reader);
						reader.getNextToken();
						break;
					case -1763474777:
						reader.getNextToken();
						_sample_ = reader.readCollection(reader_sample());
						reader.getNextToken();
						break;
					default:
						reader.getNextToken();
						reader.skip();
				}
			}
			if (reader.last() != '}') throw reader.newParseError("Expecting '}' for object end");
			return new org.krittaphato3.mcping.model.Players(_max_, _online_, _sample_);
		}
	}
}
