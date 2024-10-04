export type PropsWithStrictChildren<
  P = unknown,
  T extends React.ReactNode = React.ReactNode,
> = P & {
  children: T
}
