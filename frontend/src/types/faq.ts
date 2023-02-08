export type FaqElementType = {
    id: number;
    title: string;
    localDate:string
  };

export interface FaqInterface {
    title: string;
    content: string | undefined;
}