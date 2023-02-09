export interface NoticeInterface {
    files: File[];
    title: string;
    content: string | undefined;
}

export type NoticeElement = {
    title: string;
    content: string | undefined;
    regDate: string;
    files: String[];
}