export interface QNAListInterface {
  id: number;
  title: string;
  respond: boolean;
}

export interface QNACreateInterface {
  title: string;
  content: string;
  files?: File[];
}

export interface QNADetailInterface extends QNACreateInterface, QNAListInterface {
  regDate: string;
}
