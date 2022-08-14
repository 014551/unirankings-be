package cz.cvut.fel.unirankings.configuration.model;

/** Enum of possible job processing modes. */
public enum JobProcessingType {
  /**
   * Job processing was fully performed. Extraction results are not available in the store and
   * extraction needs to be explicitly executed.
   */
  FULL,
  /**
   * Extraction results are available in the store. Job finished without performing extraction. The
   * results are provided from the store.
   */
  CACHE
}
